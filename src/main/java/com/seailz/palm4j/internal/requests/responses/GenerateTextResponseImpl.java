package com.seailz.palm4j.internal.requests.responses;

import com.seailz.palm4j.api.entities.filter.ContentFilter;
import com.seailz.palm4j.api.entities.safety.SafetyFeedback;
import com.seailz.palm4j.api.entities.text.TextCompletion;
import com.seailz.palm4j.api.requests.responses.GenerateTextResponse;

import java.util.List;

public class GenerateTextResponseImpl implements GenerateTextResponse {
    private List<TextCompletion> options;
    private List<ContentFilter> filters;
    private List<SafetyFeedback> safetyFeedback;

    @Override
    public List<TextCompletion> getOptions() {
        return options;
    }

    public void setOptions(List<TextCompletion> options) {
        this.options = options;
    }

    @Override
    public List<ContentFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<ContentFilter> filters) {
        this.filters = filters;
    }

    @Override
    public List<SafetyFeedback> getSafetyFeedback() {
        return safetyFeedback;
    }

    public void setSafetyFeedback(List<SafetyFeedback> safetyFeedback) {
        this.safetyFeedback = safetyFeedback;
    }
}