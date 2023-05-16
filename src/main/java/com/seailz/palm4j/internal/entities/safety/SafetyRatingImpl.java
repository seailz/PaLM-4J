package com.seailz.palm4j.internal.entities.safety;

import com.seailz.palm4j.api.entities.filter.SafetySetting;
import com.seailz.palm4j.api.entities.safety.SafetyRating;

public class SafetyRatingImpl implements SafetyRating {
    private SafetySetting.HarmCategory category;
    private HarmProbability harmProbability;

    @Override
    public SafetySetting.HarmCategory getCategory() {
        return category;
    }

    public SafetyRatingImpl setCategory(SafetySetting.HarmCategory category) {
        this.category = category;
        return this;
    }

    @Override
    public HarmProbability getHarmProbability() {
        return harmProbability;
    }

    public SafetyRatingImpl setHarmProbability(HarmProbability harmProbability) {
        this.harmProbability = harmProbability;
        return this;
    }
}
