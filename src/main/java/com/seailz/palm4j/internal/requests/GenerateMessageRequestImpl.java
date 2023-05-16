package com.seailz.palm4j.internal.requests;

import com.seailz.palm4j.api.PaLM;
import com.seailz.palm4j.api.entities.Model;
import com.seailz.palm4j.api.entities.filter.ContentFilter;
import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.api.entities.message.MessageOutput;
import com.seailz.palm4j.api.entities.message.prompt.MessagePrompt;
import com.seailz.palm4j.api.requests.GenerateMessageRequest;
import com.seailz.palm4j.api.requests.responses.GenerateMessageResponse;
import com.seailz.palm4j.internal.PaLMImpl;
import com.seailz.palm4j.internal.requests.responses.GenerateMessageResponseImpl;
import com.seailz.palm4j.internal.utils.rest.Request;
import com.seailz.palm4j.internal.utils.rest.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenerateMessageRequestImpl implements GenerateMessageRequest {
    private MessagePrompt prompt;
    private PaLM paLM;
    private Model model;
    private double temperature;
    private int candidateCount = 1;
    private int topK;
    private int topP;

    @Override
    public MessagePrompt getPrompt() {
        return prompt;
    }

    @Override
    public GenerateMessageRequest setPrompt(MessagePrompt prompt) {
        this.prompt = prompt;
        return this;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public GenerateMessageRequest setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public int getCandidateCount() {
        return candidateCount;
    }

    @Override
    public GenerateMessageRequest setCandidateCount(int candidateCount) {
        this.candidateCount = candidateCount;
        return this;
    }

    @Override
    public int getTopK() {
        return topK;
    }

    @Override
    public GenerateMessageRequest setTopK(int topK) {
        this.topK = topK;
        return this;
    }

    @Override
    public int getTopP() {
        return topP;
    }

    @Override
    public GenerateMessageRequest setTopP(int topP) {
        this.topP = topP;
        return this;
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public GenerateMessageRequest setModel(Model model) {
        this.model = model;
        return this;
    }

    @Override
    public PaLM getPaLM() {
        return paLM;
    }

    @Override
    public GenerateMessageRequest setPaLM(PaLM paLM) {
        this.paLM = paLM;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("prompt", prompt.toJson());
        json.put("temperature", temperature);
        json.put("candidateCount", candidateCount);
        json.put("topK", topK);
        json.put("topP", topP);
        return json;
    }

    @Override
    public Response<GenerateMessageResponse> execute() {
        Request.Path path = new Request.Path(
                PaLMImpl.BASE_URL + "/" + PaLMImpl.API_VERSION + "/%s:generateMessage?key=%s",
                new String[]{model.getName(), paLM.getKey()},
                "POST"
        );

        Response<GenerateMessageResponse> response = new Response<>();
        new Thread(() -> {
            Request req = new Request(path, toJson());
            Request.GoogleApiResponse res = req.send();
            if (res.error()) {
                response.completeError(new Response.Error(res.errorCode(), new JSONObject(res.body())));
            } else {
                GenerateMessageResponseImpl resp = new GenerateMessageResponseImpl();

                List<Message> options = new ArrayList<>();
                JSONObject body = new JSONObject(res.body());
                if (body.has("candidates"))  {
                    JSONArray candidates = body.getJSONArray("candidates");
                    for (int i = 0; i < candidates.length(); i++) {
                        options.add(MessageOutput.fromJson(candidates.getJSONObject(i)));
                    }
                }
                resp.setOptions(options);

                List<Message> history = new ArrayList<>();
                if (body.has("messages")) {
                    JSONArray histories = body.getJSONArray("messages");
                    for (int i = 0; i < histories.length(); i++) {
                        history.add(MessageOutput.fromJson(histories.getJSONObject(i)));
                    }
                }
                resp.setHistory(history);

                List<ContentFilter> filters = new ArrayList<>();
                if (body.has("filters")) {
                    JSONArray filtersArray = body.getJSONArray("filters");
                    for (int i = 0; i < filtersArray.length(); i++) {
                        filters.add(ContentFilter.fromJson(filtersArray.getJSONObject(i)));
                    }
                }
                resp.setFilters(filters);
                response.complete(resp);
            }
        }).start();
        return response;
    }
}
