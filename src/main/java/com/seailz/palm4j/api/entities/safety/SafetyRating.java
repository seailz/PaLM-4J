package com.seailz.palm4j.api.entities.safety;

import com.seailz.palm4j.api.entities.filter.SafetySetting;

/**
 * Safety rating for a piece of content.
 * The safety rating contains the category of harm and the harm probability level in that category for a piece of content. Content is classified for safety across a number of harm categories and the probability of the harm classification is included here.
 *
 * @author Seailz
 * @since 1.0.0
 */
public interface SafetyRating {

    SafetySetting.HarmCategory getCategory();
    HarmProbability getHarmProbability();

    /**
     * The probability that a piece of content is harmful.
     */
    enum HarmProbability {
        HARM_PROBABILITY_UNSPECIFIED,
        NEGLIGIBLE,
        LOW,
        MEDIUM,
        HIGH
    }

}
