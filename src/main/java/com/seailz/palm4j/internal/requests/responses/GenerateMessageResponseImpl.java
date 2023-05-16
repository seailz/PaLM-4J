package com.seailz.palm4j.internal.requests.responses;

import com.seailz.palm4j.api.entities.filter.ContentFilter;
import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.api.requests.responses.GenerateMessageResponse;

import java.util.List;

public class GenerateMessageResponseImpl implements GenerateMessageResponse {
    private List<Message> options;
    private List<Message> history;
    private List<ContentFilter> filters;

    @Override
    public List<Message> getOptions() {
        return options;
    }

    public void setOptions(List<Message> options) {
        this.options = options;
    }

    @Override
    public List<Message> getHistory() {
        return history;
    }

    @Override
    public List<ContentFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<ContentFilter> filters) {
        this.filters = filters;
    }

    public void setHistory(List<Message> history) {
        this.history = history;
    }
}