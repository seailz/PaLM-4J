package com.seailz.palm4j.api.requests.responses;

import com.seailz.palm4j.api.entities.filter.ContentFilter;
import com.seailz.palm4j.api.entities.message.Message;

import java.util.List;

public interface GenerateMessageResponse {

    /**
     * Returns the generated messages.
     * The size of this array is defined by {@link com.seailz.palm4j.api.requests.GenerateMessageRequest#setCandidateCount(int) GenerateMessageRequest#setCandidateCount(int)}.
     */
    List<Message> getOptions();

    /**
     * Returns the message history used by the model.
     */
    List<Message> getHistory();

    /**
     * A set of content filtering metadata for the prompt and response text.
     * This indicates which SafetyCategory(s) blocked a candidate from this response, the lowest HarmProbability that triggered a block, and the HarmThreshold setting for that category. This indicates the smallest change to the SafetySettings that would be necessary to unblock at least 1 response.
     * The blocking is configured by the SafetySettings in the request (or the default SafetySettings of the API).
     */
    List<ContentFilter> getFilters();

}
