package com.seailz.palm4j.api.requests.responses;

import com.seailz.palm4j.api.entities.filter.ContentFilter;
import com.seailz.palm4j.api.entities.safety.SafetyFeedback;
import com.seailz.palm4j.api.entities.text.TextCompletion;

import java.util.List;

/**
 * The response of a {@link com.seailz.palm4j.api.requests.GenerateTextRequest}.
 *
 * @author Seailz
 * @since 1.0.0
 */
public interface GenerateTextResponse {

    /**
     * Returns the generated text.
     */
    List<TextCompletion> getOptions();

    /**
     * A set of content filtering metadata for the prompt and response text.
     * This indicates which SafetyCategory(s) blocked a candidate from this response, the lowest HarmProbability that triggered a block, and the HarmThreshold setting for that category. This indicates the smallest change to the SafetySettings that would be necessary to unblock at least 1 response.
     * The blocking is configured by the SafetySettings in the request (or the default SafetySettings of the API).
     */
    List<ContentFilter> getFilters();

    /**
     * Returns any safety feedback related to content filtering.
     */
    List<SafetyFeedback> getSafetyFeedback();

}
