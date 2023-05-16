package com.seailz.palm4j.internal.entities.message.prompt;

import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.api.entities.message.prompt.Example;
import org.json.JSONObject;

public class ExampleImpl implements Example {
    private Message input;
    private Message output;

    @Override
    public Message getInput() {
        return input;
    }

    @Override
    public Example setInput(Message input) {
        this.input = input;
        return this;
    }

    @Override
    public Message getOutput() {
        return output;
    }

    @Override
    public Example setOutput(Message output) {
        this.output = output;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("input", input.toJson());
        json.put("output", output.toJson());
        return json;
    }
}