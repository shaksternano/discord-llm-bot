@file:JvmName("Main")

package com.shakster.discordllmbot

import com.openai.client.okhttp.OpenAIOkHttpClientAsync
import com.openai.credential.BearerTokenCredential
import com.openai.models.responses.ResponseCreateParams
import dev.minn.jda.ktx.coroutines.await
import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.interactions.commands.Command
import dev.minn.jda.ktx.interactions.commands.updateCommands
import dev.minn.jda.ktx.interactions.components.getOption
import dev.minn.jda.ktx.jdabuilder.intents
import dev.minn.jda.ktx.jdabuilder.light
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.interactions.IntegrationType
import net.dv8tion.jda.api.interactions.InteractionContextType
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.requests.GatewayIntent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.io.path.Path
import kotlin.system.exitProcess
import kotlin.time.Duration.Companion.seconds

val logger: Logger = LoggerFactory.getLogger("LLM Bot")

fun main() {
    val config = Config.load(Path("config.json")) ?: exitProcess(1)

    val jda = light(config.discordBotToken, enableCoroutines = true) {
        intents += GatewayIntent.MESSAGE_CONTENT
    }

    val openAiClientBuilder = OpenAIOkHttpClientAsync.builder()
    if (config.openAiApiUrl.isNotBlank()) {
        openAiClientBuilder.baseUrl(config.openAiApiUrl)
    }
    openAiClientBuilder.credential(BearerTokenCredential.create(config.openAiApiKey))

    val openAiClient = openAiClientBuilder.build()

    jda.onCommand("chat") { event ->
        val prompt = event.getOption<String>("prompt") ?: run {
            event.reply("Please provide a prompt!")
                .setEphemeral(true)
                .await()
            return@onCommand
        }
        val model = event.getOption<String>("model")
            ?: config.models.firstOrNull()
            ?: run {
                logger.error("No models configured in config.json")
                event.reply("No models configured!")
                    .setEphemeral(true)
                    .await()
                return@onCommand
            }

        event.deferReply().await()

        coroutineScope {
            val params = ResponseCreateParams.builder()
                .input(prompt)
                .model(model)
                .build()

            var responding = true
            var replied = false
            var text = ""
            var lastSentText = ""
            val sendInterval = 0.1.seconds

            suspend fun sendUpdatedResponse(text: String) {
                if (replied) {
                    event.hook.editOriginal(text).await()
                } else {
                    event.hook.sendMessage(text).await()
                    replied = true
                }
            }

            val commandResponseJob = launch {
                while (responding) {
                    if (text != lastSentText) {
                        sendUpdatedResponse(text)
                        lastSentText = text
                        delay(sendInterval)
                    }
                }
            }

            openAiClient.streamingResponse(params).collect {
                text += it
            }

            responding = false
            commandResponseJob.join()

            if (text != lastSentText) {
                sendUpdatedResponse(text)
            }
        }
    }

    val chatCommand = Command("chat", "Chat with the LLM") {
        setContexts(InteractionContextType.ALL)
        setIntegrationTypes(IntegrationType.ALL)
        addOptions(
            OptionData(
                OptionType.STRING,
                "prompt",
                "The prompt to send to the LLM",
                true,
            ),
            OptionData(
                OptionType.STRING,
                "model",
                "The LLM model to use",
            ).addChoices(config.models.map {
                Command.Choice(it, it)
            }),
        )
    }

    jda.updateCommands {
        addCommands(chatCommand)
    }.queue()
}
