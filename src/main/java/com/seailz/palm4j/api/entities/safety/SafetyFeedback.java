package com.seailz.palm4j.api.entities.safety;

import com.seailz.palm4j.api.entities.filter.SafetySetting;
import com.seailz.palm4j.internal.entities.filter.SafetySettingImpl;
import com.seailz.palm4j.internal.entities.safety.SafetyFeedbackImpl;
import com.seailz.palm4j.internal.entities.safety.SafetyRatingImpl;
import org.json.JSONObject;

/**
 * Safety feedback for an entire request.
 */
public interface SafetyFeedback {

    /**
     * Safety rating evaluated from content.
     */
    SafetyRating getRating();

    /**
     * Safety settings applied to the request.
     */
    SafetySetting getSetting();

    static SafetyFeedback fromJson(JSONObject obj) {
        SafetyFeedbackImpl safetyFeedback = new SafetyFeedbackImpl();

        SafetyRatingImpl rating = new SafetyRatingImpl();
        rating.setCategory(SafetySetting.HarmCategory.valueOf(obj.getJSONObject("rating").getString("category")));
        rating.setHarmProbability(SafetyRating.HarmProbability.valueOf(obj.getJSONObject("rating").getString("probability")));

        SafetySettingImpl setting = new SafetySettingImpl();
        setting.setCategory(SafetySetting.HarmCategory.valueOf(obj.getJSONObject("setting").getString("category")));
        setting.setHarmThreshold(SafetySetting.HarmBlockThreshold.valueOf(obj.getJSONObject("setting").getString("threshold")));

        safetyFeedback.setRating(rating);
        safetyFeedback.setSetting(setting);
        return safetyFeedback;
    }

}
