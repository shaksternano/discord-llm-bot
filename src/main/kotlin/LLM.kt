package com.shakster.discordllmbot

import com.openai.client.OpenAIClientAsync
import com.openai.models.responses.ResponseCompletedEvent
import com.openai.models.responses.ResponseCreateParams
import com.openai.models.responses.ResponseTextDeltaEvent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

fun OpenAIClientAsync.streamingResponse(
    params: ResponseCreateParams,
    startTimeout: Duration = 1.minutes,
    updateTimeout: Duration = 5.seconds,
    finishTimeout: Duration = 5.minutes,
): Flow<String> {
    return callbackFlow {
        var started = false
        var doTimeout: Boolean

        val timeoutJob = launch {
            delay(startTimeout)
            if (!started) {
                close()
            } else {
                delay(finishTimeout)
                close()
            }
        }

        val updateTimeoutJob = launch {
            @OptIn(DelicateCoroutinesApi::class)
            while (!isClosedForSend) {
                if (started) {
                    doTimeout = true
                    delay(updateTimeout)
                    if (doTimeout) {
                        close()
                    }
                }
            }
        }

        responses()
            .createStreaming(params)
            .subscribe {
                when (val response = it.accept(OutputTextDeltaVisitor)) {
                    is StreamingResponse.TextDelta -> {
                        started = true
                        doTimeout = false
                        trySend(response.delta)
                    }

                    StreamingResponse.Done -> close()
                    else -> {}
                }
            }

        awaitClose {
            timeoutJob.cancel()
            updateTimeoutJob.cancel()
        }
    }.buffer(Channel.UNLIMITED)
}

private object OutputTextDeltaVisitor : ResponseVisitor<StreamingResponse>() {
    override fun visitOutputTextDelta(outputTextDelta: ResponseTextDeltaEvent): StreamingResponse {
        return StreamingResponse.TextDelta(outputTextDelta.delta())
    }

    override fun visitCompleted(completed: ResponseCompletedEvent): StreamingResponse {
        return StreamingResponse.Done
    }
}

private sealed interface StreamingResponse {
    data class TextDelta(val delta: String) : StreamingResponse

    object Done : StreamingResponse
}
