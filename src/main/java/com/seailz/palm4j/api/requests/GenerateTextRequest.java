package com.seailz.palm4j.api.requests;

import com.seailz.palm4j.api.PaLM;
import com.seailz.palm4j.api.entities.Model;
import com.seailz.palm4j.api.entities.filter.SafetySetting;
import com.seailz.palm4j.api.requests.responses.GenerateMessageResponse;
import com.seailz.palm4j.api.requests.responses.GenerateTextResponse;
import com.seailz.palm4j.internal.requests.GenerateTextRequestImpl;
import com.seailz.palm4j.internal.utils.rest.Response;
import org.json.JSONObject;

import java.util.List;

public interface GenerateTextRequest {
        /**
         * The prompt that will be used to generate a message.
         */
        String getPrompt();
        GenerateTextRequest setPrompt(String prompt);

        /**
         * Optional. Controls the randomness of the output.
         * Values can range over [0.0,1.0], inclusive. A value closer to 1.0 will produce responses that are more varied, while a value closer to 0.0 will typically result in less surprising responses from the model.
         */
        double getTemperature();
        GenerateTextRequest setTemperature(double temperature);

        /**
         * Optional. The number of generated response messages to return.
         * This value must be between [1, 8], inclusive. If unset, this will default to 1.
         */
        int getCandidateCount();
        GenerateTextRequest setCandidateCount(int candidateCount);

        /**
         * Optional. The maximum number of tokens to consider when sampling.
         * The model uses combined Top-k and nucleus sampling.
         * Top-k sampling considers the set of topK most probable tokens.
         */
        int getTopK();
        GenerateTextRequest setTopK(int topK);

        /**
         * Optional. The maximum cumulative probability of tokens to consider when sampling.
         * The model uses combined Top-k and nucleus sampling.
         * Nucleus sampling considers the smallest set of tokens whose probability sum is at least topP.
         */
        int getTopP();
        GenerateTextRequest setTopP(int topP);

        List<SafetySetting> getSafetySettings();
        GenerateTextRequest setSafetySettings(List<SafetySetting> safetySettings);
        GenerateTextRequest addSafetySetting(SafetySetting safetySetting);

        List<String> getStopSequences();
        GenerateTextRequest setStopSequences(List<String> stopSequences);
        GenerateTextRequest addStopSequence(String stopSequence);

        int maxOutputTokens();


        /**
         * Required. The model to use for generating the message.
         */
        Model getModel();
        GenerateTextRequest setModel(Model model);

        PaLM getPaLM();
        GenerateTextRequest setPaLM(PaLM paLM);


        JSONObject toJson();

        Response<GenerateTextResponse> execute();

        static GenerateTextRequest newRequest() {
                return new GenerateTextRequestImpl();
        }
}
