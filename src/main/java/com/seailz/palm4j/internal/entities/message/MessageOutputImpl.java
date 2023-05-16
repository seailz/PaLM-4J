package com.seailz.palm4j.internal.entities.message;

import com.seailz.palm4j.api.entities.message.CitationSource;
import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.api.entities.message.MessageOutput;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MessageOutputImpl implements MessageOutput {
    private List<CitationSource> citations;
    private String author;
    private String content;

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public MessageOutputImpl setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public MessageOutputImpl setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public List<CitationSource> getCitations() {
        return citations;
    }

    public MessageOutput setCitations(List<CitationSource> citations) {
        this.citations = citations;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("author", getAuthor());
        json.put("content", getContent());
        JSONArray citationsJson = new JSONArray();
        for (CitationSource citation : citations) {
            citationsJson.put(citation.toJson());
        }
        json.put("citationMetadata", new JSONObject().put("citationSources", citationsJson));
        return json;
    }
}