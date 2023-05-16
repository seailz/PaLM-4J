package com.seailz.palm4j.internal.entities.safety;

import com.seailz.palm4j.api.entities.filter.SafetySetting;
import com.seailz.palm4j.api.entities.safety.SafetyFeedback;
import com.seailz.palm4j.api.entities.safety.SafetyRating;

public class SafetyFeedbackImpl implements SafetyFeedback {
    private SafetyRating rating;
    private SafetySetting setting;

    @Override
    public SafetyRating getRating() {
        return rating;
    }

    @Override
    public SafetySetting getSetting() {
        return setting;
    }

    public SafetyFeedback setRating(SafetyRating rating) {
        this.rating = rating;
        return this;
    }

    public SafetyFeedback setSetting(SafetySetting setting) {
        this.setting = setting;
        return this;
    }
}