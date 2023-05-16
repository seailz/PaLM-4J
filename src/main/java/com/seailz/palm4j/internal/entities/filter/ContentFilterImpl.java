package com.seailz.palm4j.internal.entities.filter;

import com.seailz.palm4j.api.entities.filter.BlockedReason;
import com.seailz.palm4j.api.entities.filter.ContentFilter;

public class ContentFilterImpl implements ContentFilter {

    private BlockedReason reason;
    private String message;

    @Override
    public BlockedReason getReason() {
        return reason;
    }

    public ContentFilterImpl setReason(BlockedReason reason) {
        this.reason = reason;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ContentFilterImpl setMessage(String message) {
        this.message = message;
        return this;
    }
}
