package com.shakster.discordllmbot

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import java.nio.file.Path
import kotlin.io.path.*

@Serializable
data class Config(
    val discordBotToken: String,
    val openAiApiUrl: String = "",
    val openAiApiKey: String = "",
    val models: List<ModelConfig> = listOf(),
) {
    @Serializable
    data class ModelConfig(
        val name: String,
        val params: JsonObject = buildJsonObject {},
    ) {
        val paramsMap: Map<String, Any?>
            @Suppress("UNCHECKED_CAST")
            get() = toRaw(params) as Map<String, Any?>

        private fun toRaw(json: JsonElement): Any? {
            return when (json) {
                is JsonPrimitive -> {
                    json.doubleOrNull ?: json.booleanOrNull ?: json.contentOrNull
                }

                is JsonArray -> json.map { toRaw(it) }

                is JsonObject -> json.mapValues { toRaw(it.value) }
            }
        }
    }

    companion object {
        fun load(path: Path): Config? {
            val json = Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
                allowTrailingComma = true
                allowComments = true
            }

            if (!path.exists()) {
                val defaultConfig = Config("")
                val json = json.encodeToString(defaultConfig)
                path.writeText(json)
                logger.error("Config file not found at ${path.absolute()}, created a new one.")
                return null
            }

            if (!path.isRegularFile()) {
                logger.error("Config path must be a file: ${path.absolute()}")
                return null
            }

            val configContent = path.readText()
            val config = try {
                json.decodeFromString<Config>(configContent)
            } catch (t: Throwable) {
                logger.error("Failed to parse config file: ${path.absolute()}", t)
                return null
            }

            if (config.discordBotToken.isBlank()) {
                logger.error("Discord bot token is missing in config file: ${path.absolute()}")
                return null
            }

            if (config.models.isEmpty()) {
                logger.error("No models specified in config file: ${path.absolute()}")
                return null
            }

            return config
        }
    }
}
