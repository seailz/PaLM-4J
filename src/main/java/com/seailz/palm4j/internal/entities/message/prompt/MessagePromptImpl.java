package com.seailz.palm4j.internal.entities.message.prompt;

import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.api.entities.message.prompt.Example;
import com.seailz.palm4j.api.entities.message.prompt.MessagePrompt;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessagePromptImpl implements MessagePrompt {
    private String context;
    private List<Example> examples;
    private List<Message> messages;

    public MessagePromptImpl() {
        this.examples = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public MessagePrompt setContext(String context) {
        this.context = context;
        return this;
    }

    @Override
    public List<Example> getExamples() {
        return examples;
    }

    @Override
    public MessagePrompt setExamples(List<Example> examples) {
        this.examples = examples;
        return this;
    }

    @Override
    public MessagePrompt addExample(Example example) {
        this.examples.add(example);
        return this;
    }

    @Override
    public MessagePrompt addExamples(Example... examples) {
        this.examples.addAll(Arrays.asList(examples));
        return this;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public MessagePrompt setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

    @Override
    public MessagePrompt addMessage(Message message) {
        this.messages.add(message);
        return this;
    }

    @Override
    public MessagePrompt addMessages(Message... messages) {
        this.messages.addAll(Arrays.asList(messages));
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONArray examples = new JSONArray();
        for (Example example : this.examples) {
            examples.put(example.toJson());
        }

        JSONArray messages = new JSONArray();
        for (Message message : this.messages) {
            messages.put(message.toJson());
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("context", context);
        jsonObject.put("examples", examples);
        jsonObject.put("messages", messages);
        return jsonObject;
    }
}
