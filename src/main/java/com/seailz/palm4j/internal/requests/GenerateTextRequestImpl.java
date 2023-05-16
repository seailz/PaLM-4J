package com.seailz.palm4j.internal.requests;

import com.seailz.palm4j.api.PaLM;
import com.seailz.palm4j.api.entities.Model;
import com.seailz.palm4j.api.entities.filter.ContentFilter;
import com.seailz.palm4j.api.entities.filter.SafetySetting;
import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.api.entities.message.MessageOutput;
import com.seailz.palm4j.api.entities.safety.SafetyFeedback;
import com.seailz.palm4j.api.entities.text.TextCompletion;
import com.seailz.palm4j.api.requests.GenerateMessageRequest;
import com.seailz.palm4j.api.requests.GenerateTextRequest;
import com.seailz.palm4j.api.requests.responses.GenerateMessageResponse;
import com.seailz.palm4j.api.requests.responses.GenerateTextResponse;
import com.seailz.palm4j.internal.PaLMImpl;
import com.seailz.palm4j.internal.requests.responses.GenerateMessageResponseImpl;
import com.seailz.palm4j.internal.requests.responses.GenerateTextResponseImpl;
import com.seailz.palm4j.internal.utils.rest.Request;
import com.seailz.palm4j.internal.utils.rest.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenerateTextRequestImpl implements GenerateTextRequest {
    private String prompt;
    private double temperature;
    private int candidateCount;
    private int topK;
    private int topP;
    private List<SafetySetting> safetySettings;
    private List<String> stopSequences;
    private Model model;
    private PaLM paLM;

    @Override
    public String getPrompt() {
        return prompt;
    }

    @Override
    public GenerateTextRequest setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public GenerateTextRequest setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public int getCandidateCount() {
        return candidateCount;
    }

    @Override
    public GenerateTextRequest setCandidateCount(int candidateCount) {
        this.candidateCount = candidateCount;
        return this;
    }

    @Override
    public int getTopK() {
        return topK;
    }

    @Override
    public GenerateTextRequest setTopK(int topK) {
        this.topK = topK;
        return this;
    }

    @Override
    public int getTopP() {
        return topP;
    }

    @Override
    public GenerateTextRequest setTopP(int topP) {
        this.topP = topP;
        return this;
    }

    @Override
    public List<SafetySetting> getSafetySettings() {
        return safetySettings;
    }

    @Override
    public GenerateTextRequest setSafetySettings(List<SafetySetting> safetySettings) {
        this.safetySettings = safetySettings;
        return this;
    }

    @Override
    public GenerateTextRequest addSafetySetting(SafetySetting safetySetting) {
        if (safetySettings == null) {
            safetySettings = new ArrayList<>();
        }
        safetySettings.add(safetySetting);
        return this;
    }

    @Override
    public List<String> getStopSequences() {
        return stopSequences;
    }

    @Override
    public GenerateTextRequest setStopSequences(List<String> stopSequences) {
        this.stopSequences = stopSequences;
        return this;
    }

    @Override
    public GenerateTextRequest addStopSequence(String stopSequence) {
        if (stopSequences == null) {
            stopSequences = new ArrayList<>();
        }
        stopSequences.add(stopSequence);
        return this;
    }

    @Override
    public int maxOutputTokens() {
        // Implementation of the maxOutputTokens method is not provided in the code snippet.
        // You can add the logic here to calculate the maximum number of output tokens based on your requirements.
        return 0;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public GenerateTextRequest setModel(Model model) {
        this.model = model;
        return this;
    }

    @Override
    public PaLM getPaLM() {
        return paLM;
    }

    @Override
    public GenerateTextRequest setPaLM(PaLM paLM) {
        this.paLM = paLM;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        // Convert the object's properties to JSON representation

        JSONArray safetySettingsJsonArray = new JSONArray();
        if (safetySettings != null) {
            for (SafetySetting safetySetting : safetySettings) {
                safetySettingsJsonArray.put(safetySetting.toJson());
            }
        }

        JSONArray stopSequencesJsonArray = new JSONArray();
        if (stopSequences != null) {
            for (String stopSequence : stopSequences) {
                stopSequencesJsonArray.put(stopSequence);
            }
        }

        jsonObject.put("prompt", new JSONObject().put("text", prompt));
        if (temperature != 0) jsonObject.put("temperature", temperature);
        if (candidateCount != 0) jsonObject.put("candidateCount", candidateCount);
        if (topK != 0)jsonObject.put("topK", topK);
        if (topP != 0) jsonObject.put("topP", topP);
        if (!safetySettingsJsonArray.isEmpty()) jsonObject.put("safetySettings", safetySettingsJsonArray);
        if (!stopSequencesJsonArray.isEmpty()) jsonObject.put("stopSequences", stopSequencesJsonArray);
        return jsonObject;
    }

    @Override
    public Response<GenerateTextResponse> execute() {
        Request.Path path = new Request.Path(
                PaLMImpl.BASE_URL + "/" + PaLMImpl.API_VERSION + "/%s:generateText?key=%s",
                new String[]{model.getName(), paLM.getKey()},
                "POST"
        );

        Response<GenerateTextResponse> response = new Response<>();
        new Thread(() -> {
            Request req = new Request(path, toJson());
            Request.GoogleApiResponse res = req.send();
            System.out.println(toJson());
            if (res.error()) {
                response.completeError(new Response.Error(res.errorCode(), new JSONObject(res.body())));
            } else {
                GenerateTextResponseImpl resp = new GenerateTextResponseImpl();

                List<TextCompletion> options = new ArrayList<>();
                JSONObject body = new JSONObject(res.body());
                if (body.has("candidates"))  {
                    JSONArray candidates = body.getJSONArray("candidates");
                    for (int i = 0; i < candidates.length(); i++) {
                        options.add(TextCompletion.fromJson(candidates.getJSONObject(i)));
                    }
                }
                resp.setOptions(options);

                List<ContentFilter> filters = new ArrayList<>();
                if (body.has("filters")) {
                    JSONArray filtersArray = body.getJSONArray("filters");
                    for (int i = 0; i < filtersArray.length(); i++) {
                        filters.add(ContentFilter.fromJson(filtersArray.getJSONObject(i)));
                    }
                }
                resp.setFilters(filters);

                List<SafetyFeedback> feedback = new ArrayList<>();
                if (body.has("safetyFeedback")) {
                    JSONArray feedbackArray = body.getJSONArray("safetyFeedback");
                    for (int i = 0; i < feedbackArray.length(); i++) {
                        feedback.add(SafetyFeedback.fromJson(feedbackArray.getJSONObject(i)));
                    }
                }

                response.complete(resp);
            }
        }).start();
        return response;
    }
}
