package com.seailz.palm4j.api.entities.message;

import com.seailz.palm4j.internal.entities.message.CitationSourceImpl;
import org.json.JSONObject;

/**
 * A citation to a source for a portion of a specific response.
 * @author Seailz
 * @since 1.0.0
 */
public interface CitationSource {

    /**
     * Optional. Start of segment of the response that is attributed to this source.
     * Index indicates the start of the segment, measured in bytes.
     */
    int getStartIndex();

    /**
     * Optional. End of the attributed segment, exclusive.
     */
    int getEndIndex();

    /**
     * Optional. URI that is attributed as a source for a portion of the text.
     */
    String getUri();

    /**
     * Optional. License for the GitHub project that is attributed as a source for segment.
     * <br>License info is required for code citations.
     */
    String getLicense();

    JSONObject toJson();

    static CitationSource fromJson(JSONObject obj) {
        return new CitationSourceImpl()
                .setEndIndex(obj.has("endIndex") ? obj.optInt("endIndex") : -1)
                .setStartIndex(obj.has("startIndex") ? obj.optInt("startIndex") : -1)
                .setLicense(obj.has("license") ? obj.optString("license") : null)
                .setUri(obj.has("uri") ? obj.optString("uri") : null);
    }


}
