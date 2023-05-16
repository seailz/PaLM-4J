package com.seailz.palm4j.internal.entities.message;

import com.seailz.palm4j.api.entities.message.Message;
import org.json.JSONObject;

public class MessageImpl implements Message {
    private String author;
    private String content;

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public Message setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("author", author);
        json.put("content", content);
        return json;
    }
}

