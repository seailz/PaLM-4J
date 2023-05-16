package com.seailz.palm4j.api.entities.filter;

import com.seailz.palm4j.internal.entities.filter.SafetySettingImpl;
import org.json.JSONObject;

/**
 * Safety setting, affecting the safety-blocking behavior.
 */
public interface SafetySetting {

    /**
     * Returns the safety category.
     */
    HarmCategory getCategory();
    SafetySetting setCategory(HarmCategory category);

    HarmBlockThreshold getHarmThreshold();
    SafetySetting setHarmThreshold(HarmBlockThreshold harmThreshold);

    static SafetySetting newSafetySetting() {
        return new SafetySettingImpl();
    }

    JSONObject toJson();


    public enum HarmCategory {
        HARM_CATEGORY_UNSPECIFIED,
        HARM_CATEGORY_DEROGATORY,
        HARM_CATEGORY_TOXICITY,
        HARM_CATEGORY_VIOLENCE,
        HARM_CATEGORY_SEXUAL,
        HARM_CATEGORY_MEDICAL,
        HARM_CATEGORY_DANGEROUS
    }

    public enum HarmBlockThreshold {
        HARM_BLOCK_THRESHOLD_UNSPECIFIED,
        BLOCK_LOW_AND_ABOVE,
        BLOCK_MEDIUM_AND_ABOVE,
        BLOCK_ONLY_HIGH,
        BLOCK_NONE
    }

}


