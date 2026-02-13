package com.shakster.discordllmbot

import com.openai.models.responses.ResponseAudioDeltaEvent
import com.openai.models.responses.ResponseAudioDoneEvent
import com.openai.models.responses.ResponseAudioTranscriptDeltaEvent
import com.openai.models.responses.ResponseAudioTranscriptDoneEvent
import com.openai.models.responses.ResponseCodeInterpreterCallCodeDeltaEvent
import com.openai.models.responses.ResponseCodeInterpreterCallCodeDoneEvent
import com.openai.models.responses.ResponseCodeInterpreterCallCompletedEvent
import com.openai.models.responses.ResponseCodeInterpreterCallInProgressEvent
import com.openai.models.responses.ResponseCodeInterpreterCallInterpretingEvent
import com.openai.models.responses.ResponseCompletedEvent
import com.openai.models.responses.ResponseContentPartAddedEvent
import com.openai.models.responses.ResponseContentPartDoneEvent
import com.openai.models.responses.ResponseCreatedEvent
import com.openai.models.responses.ResponseCustomToolCallInputDeltaEvent
import com.openai.models.responses.ResponseCustomToolCallInputDoneEvent
import com.openai.models.responses.ResponseErrorEvent
import com.openai.models.responses.ResponseFailedEvent
import com.openai.models.responses.ResponseFileSearchCallCompletedEvent
import com.openai.models.responses.ResponseFileSearchCallInProgressEvent
import com.openai.models.responses.ResponseFileSearchCallSearchingEvent
import com.openai.models.responses.ResponseFunctionCallArgumentsDeltaEvent
import com.openai.models.responses.ResponseFunctionCallArgumentsDoneEvent
import com.openai.models.responses.ResponseImageGenCallCompletedEvent
import com.openai.models.responses.ResponseImageGenCallGeneratingEvent
import com.openai.models.responses.ResponseImageGenCallInProgressEvent
import com.openai.models.responses.ResponseImageGenCallPartialImageEvent
import com.openai.models.responses.ResponseInProgressEvent
import com.openai.models.responses.ResponseIncompleteEvent
import com.openai.models.responses.ResponseMcpCallArgumentsDeltaEvent
import com.openai.models.responses.ResponseMcpCallArgumentsDoneEvent
import com.openai.models.responses.ResponseMcpCallCompletedEvent
import com.openai.models.responses.ResponseMcpCallFailedEvent
import com.openai.models.responses.ResponseMcpCallInProgressEvent
import com.openai.models.responses.ResponseMcpListToolsCompletedEvent
import com.openai.models.responses.ResponseMcpListToolsFailedEvent
import com.openai.models.responses.ResponseMcpListToolsInProgressEvent
import com.openai.models.responses.ResponseOutputItemAddedEvent
import com.openai.models.responses.ResponseOutputItemDoneEvent
import com.openai.models.responses.ResponseOutputTextAnnotationAddedEvent
import com.openai.models.responses.ResponseQueuedEvent
import com.openai.models.responses.ResponseReasoningSummaryPartAddedEvent
import com.openai.models.responses.ResponseReasoningSummaryPartDoneEvent
import com.openai.models.responses.ResponseReasoningSummaryTextDeltaEvent
import com.openai.models.responses.ResponseReasoningSummaryTextDoneEvent
import com.openai.models.responses.ResponseReasoningTextDeltaEvent
import com.openai.models.responses.ResponseReasoningTextDoneEvent
import com.openai.models.responses.ResponseRefusalDeltaEvent
import com.openai.models.responses.ResponseRefusalDoneEvent
import com.openai.models.responses.ResponseStreamEvent
import com.openai.models.responses.ResponseTextDeltaEvent
import com.openai.models.responses.ResponseTextDoneEvent
import com.openai.models.responses.ResponseWebSearchCallCompletedEvent
import com.openai.models.responses.ResponseWebSearchCallInProgressEvent
import com.openai.models.responses.ResponseWebSearchCallSearchingEvent

open class ResponseVisitor<T> : ResponseStreamEvent.Visitor<T?> {
    override fun visitAudioDelta(audioDelta: ResponseAudioDeltaEvent): T? = null

    override fun visitAudioDone(audioDone: ResponseAudioDoneEvent): T? = null

    override fun visitAudioTranscriptDelta(audioTranscriptDelta: ResponseAudioTranscriptDeltaEvent): T? = null

    override fun visitAudioTranscriptDone(audioTranscriptDone: ResponseAudioTranscriptDoneEvent): T? = null

    override fun visitCodeInterpreterCallCodeDelta(codeInterpreterCallCodeDelta: ResponseCodeInterpreterCallCodeDeltaEvent): T? =
        null

    override fun visitCodeInterpreterCallCodeDone(codeInterpreterCallCodeDone: ResponseCodeInterpreterCallCodeDoneEvent): T? =
        null

    override fun visitCodeInterpreterCallCompleted(codeInterpreterCallCompleted: ResponseCodeInterpreterCallCompletedEvent): T? =
        null

    override fun visitCodeInterpreterCallInProgress(codeInterpreterCallInProgress: ResponseCodeInterpreterCallInProgressEvent): T? =
        null

    override fun visitCodeInterpreterCallInterpreting(codeInterpreterCallInterpreting: ResponseCodeInterpreterCallInterpretingEvent): T? =
        null

    override fun visitCompleted(completed: ResponseCompletedEvent): T? = null

    override fun visitContentPartAdded(contentPartAdded: ResponseContentPartAddedEvent): T? = null

    override fun visitContentPartDone(contentPartDone: ResponseContentPartDoneEvent): T? = null

    override fun visitCreated(created: ResponseCreatedEvent): T? = null

    override fun visitCustomToolCallInputDelta(customToolCallInputDelta: ResponseCustomToolCallInputDeltaEvent): T? =
        null

    override fun visitCustomToolCallInputDone(customToolCallInputDone: ResponseCustomToolCallInputDoneEvent): T? = null

    override fun visitError(error: ResponseErrorEvent): T? = null

    override fun visitFailed(failed: ResponseFailedEvent): T? = null

    override fun visitFileSearchCallCompleted(fileSearchCallCompleted: ResponseFileSearchCallCompletedEvent): T? = null

    override fun visitFileSearchCallInProgress(fileSearchCallInProgress: ResponseFileSearchCallInProgressEvent): T? =
        null

    override fun visitFileSearchCallSearching(fileSearchCallSearching: ResponseFileSearchCallSearchingEvent): T? = null

    override fun visitFunctionCallArgumentsDelta(functionCallArgumentsDelta: ResponseFunctionCallArgumentsDeltaEvent): T? =
        null

    override fun visitFunctionCallArgumentsDone(functionCallArgumentsDone: ResponseFunctionCallArgumentsDoneEvent): T? =
        null

    override fun visitImageGenerationCallCompleted(imageGenerationCallCompleted: ResponseImageGenCallCompletedEvent): T? =
        null

    override fun visitImageGenerationCallGenerating(imageGenerationCallGenerating: ResponseImageGenCallGeneratingEvent): T? =
        null

    override fun visitImageGenerationCallInProgress(imageGenerationCallInProgress: ResponseImageGenCallInProgressEvent): T? =
        null

    override fun visitImageGenerationCallPartialImage(imageGenerationCallPartialImage: ResponseImageGenCallPartialImageEvent): T? =
        null

    override fun visitInProgress(inProgress: ResponseInProgressEvent): T? = null

    override fun visitIncomplete(incomplete: ResponseIncompleteEvent): T? = null

    override fun visitMcpCallArgumentsDelta(mcpCallArgumentsDelta: ResponseMcpCallArgumentsDeltaEvent): T? = null

    override fun visitMcpCallArgumentsDone(mcpCallArgumentsDone: ResponseMcpCallArgumentsDoneEvent): T? = null

    override fun visitMcpCallCompleted(mcpCallCompleted: ResponseMcpCallCompletedEvent): T? = null

    override fun visitMcpCallFailed(mcpCallFailed: ResponseMcpCallFailedEvent): T? = null

    override fun visitMcpCallInProgress(mcpCallInProgress: ResponseMcpCallInProgressEvent): T? = null

    override fun visitMcpListToolsCompleted(mcpListToolsCompleted: ResponseMcpListToolsCompletedEvent): T? = null

    override fun visitMcpListToolsFailed(mcpListToolsFailed: ResponseMcpListToolsFailedEvent): T? = null

    override fun visitMcpListToolsInProgress(mcpListToolsInProgress: ResponseMcpListToolsInProgressEvent): T? = null

    override fun visitOutputItemAdded(outputItemAdded: ResponseOutputItemAddedEvent): T? = null

    override fun visitOutputItemDone(outputItemDone: ResponseOutputItemDoneEvent): T? = null

    override fun visitOutputTextAnnotationAdded(outputTextAnnotationAdded: ResponseOutputTextAnnotationAddedEvent): T? =
        null

    override fun visitOutputTextDelta(outputTextDelta: ResponseTextDeltaEvent): T? = null

    override fun visitOutputTextDone(outputTextDone: ResponseTextDoneEvent): T? = null

    override fun visitQueued(queued: ResponseQueuedEvent): T? = null

    override fun visitReasoningSummaryPartAdded(reasoningSummaryPartAdded: ResponseReasoningSummaryPartAddedEvent): T? =
        null

    override fun visitReasoningSummaryPartDone(reasoningSummaryPartDone: ResponseReasoningSummaryPartDoneEvent): T? =
        null

    override fun visitReasoningSummaryTextDelta(reasoningSummaryTextDelta: ResponseReasoningSummaryTextDeltaEvent): T? =
        null

    override fun visitReasoningSummaryTextDone(reasoningSummaryTextDone: ResponseReasoningSummaryTextDoneEvent): T? =
        null

    override fun visitReasoningTextDelta(reasoningTextDelta: ResponseReasoningTextDeltaEvent): T? = null

    override fun visitReasoningTextDone(reasoningTextDone: ResponseReasoningTextDoneEvent): T? = null

    override fun visitRefusalDelta(refusalDelta: ResponseRefusalDeltaEvent): T? = null

    override fun visitRefusalDone(refusalDone: ResponseRefusalDoneEvent): T? = null

    override fun visitWebSearchCallCompleted(webSearchCallCompleted: ResponseWebSearchCallCompletedEvent): T? = null

    override fun visitWebSearchCallInProgress(webSearchCallInProgress: ResponseWebSearchCallInProgressEvent): T? = null

    override fun visitWebSearchCallSearching(webSearchCallSearching: ResponseWebSearchCallSearchingEvent): T? = null
}
