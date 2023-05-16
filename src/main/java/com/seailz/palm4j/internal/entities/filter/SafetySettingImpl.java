package com.seailz.palm4j.internal.entities.filter;

import com.seailz.palm4j.api.entities.filter.SafetySetting;
import org.json.JSONObject;

import org.json.JSONObject;

public class SafetySettingImpl implements SafetySetting {
    private HarmCategory category;
    private HarmBlockThreshold harmThreshold;

    @Override
    public HarmCategory getCategory() {
        return category;
    }

    @Override
    public SafetySetting setCategory(HarmCategory category) {
        this.category = category;
        return this;
    }

    @Override
    public HarmBlockThreshold getHarmThreshold() {
        return harmThreshold;
    }

    @Override
    public SafetySetting setHarmThreshold(HarmBlockThreshold harmThreshold) {
        this.harmThreshold = harmThreshold;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("category", category);
        obj.put("threshold", harmThreshold);
        return obj;
    }

    // Additional methods or setters if needed
}
