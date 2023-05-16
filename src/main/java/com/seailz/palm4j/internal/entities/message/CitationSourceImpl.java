package com.seailz.palm4j.internal.entities.message;

import com.seailz.palm4j.api.entities.message.CitationSource;
import org.json.JSONObject;

public class CitationSourceImpl implements CitationSource {
    private int startIndex;
    private int endIndex;
    private String uri;
    private String license;

    @Override
    public int getStartIndex() {
        return startIndex;
    }

    public CitationSourceImpl setStartIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    @Override
    public int getEndIndex() {
        return endIndex;
    }

    public CitationSourceImpl setEndIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    @Override
    public String getUri() {
        return uri;
    }

    public CitationSourceImpl setUri(String uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public String getLicense() {
        return license;
    }

    public CitationSourceImpl setLicense(String license) {
        this.license = license;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("startIndex", startIndex);
        json.put("endIndex", endIndex);
        json.put("uri", uri);
        json.put("license", license);
        return json;
    }
}
