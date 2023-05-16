package com.seailz.palm4j.api.entities.filter;

import com.seailz.palm4j.internal.entities.filter.ContentFilterImpl;
import org.json.JSONObject;

/**
 * Content filtering metadata associated with processing a single request.
 * ContentFilter contains a reason and an optional supporting string. The reason may be unspecified.
 *
 * @author Seailz
 * @version 1.0.0
 */
public interface ContentFilter {

    /**
     * The reason content was blocked during request processing.
     */
    BlockedReason getReason();

    /**
     * A string that describes the filtering behavior in more detail.
     */
    String getMessage();

    static ContentFilter fromJson(JSONObject obj) {
        return new ContentFilterImpl()
                .setReason(obj.has("reason") ? BlockedReason.valueOf(obj.optString("reason")) : null)
                .setMessage(obj.has("message") ? obj.optString("message") : null);
    }

}
