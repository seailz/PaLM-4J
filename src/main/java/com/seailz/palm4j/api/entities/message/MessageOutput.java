package com.seailz.palm4j.api.entities.message;

import com.seailz.palm4j.internal.entities.message.MessageImpl;
import com.seailz.palm4j.internal.entities.message.MessageOutputImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * An output message from the model.
 */
public interface MessageOutput extends Message {

    List<CitationSource> getCitations();

    @Override
    JSONObject toJson();

    static MessageOutput fromJson(JSONObject json) {
        List<CitationSource> citations = new ArrayList<>();
        if (json.has("citationMetadata")) {
            JSONObject citationMetadata = json.getJSONObject("citationMetadata");
            JSONArray sources = citationMetadata.getJSONArray("citationSources");
            for (Object source : sources) {
                citations.add(CitationSource.fromJson((JSONObject) source));
            }
        }

        return new MessageOutputImpl()
                .setAuthor(json.has("author") ? json.optString("author") : null)
                .setContent(json.has("content") ? json.optString("content") : null)
                .setCitations(citations);
    }
}
