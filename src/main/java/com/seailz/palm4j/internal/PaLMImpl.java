package com.seailz.palm4j.internal;

import com.seailz.palm4j.api.PaLM;
import com.seailz.palm4j.api.entities.Model;
import com.seailz.palm4j.api.entities.message.prompt.MessagePrompt;
import com.seailz.palm4j.internal.entities.ModelImpl;
import com.seailz.palm4j.internal.utils.rest.Request;
import com.seailz.palm4j.internal.utils.rest.Response;
import org.jetbrains.annotations.CheckReturnValue;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaLMImpl implements PaLM {

    public static final String BASE_URL = "https://generativelanguage.googleapis.com";
    public static final String API_VERSION = "v1beta2";

    private final String key;


    public PaLMImpl(String key) {
        this.key = key;
    }


    @Override
    public String getKey() {
        return key;
    }

    @Override
    @CheckReturnValue
    public Response<List<Model>> getModels() {
        Request.Path path = new Request.Path(
                BASE_URL + "/" + API_VERSION + "/models?key=%s",
                new String[]{key},
                "GET"
        );

        Response<List<Model>> response = new Response<>();
        new Thread(() -> {
            Request req = new Request(path, new JSONObject());
            Request.GoogleApiResponse res = req.send();
            if (res.error()) {
                response.completeError(new Response.Error(res.errorCode(), new JSONObject(res.body())));
            } else {
                List<Model> models = new ArrayList<>();
                JSONObject body = new JSONObject(res.body());
                for (Object o : body.getJSONArray("models")) {
                    models.add(ModelImpl.fromJson((JSONObject) o));
                }
                response.complete(models);
            }
        }).start();
        return response;
    }

    @Override
    @CheckReturnValue
    public Response<Model> getModel(String name) {
        Request.Path path = new Request.Path(
                BASE_URL + "/" + API_VERSION + "/%s?key=%s",
                new String[]{name, key},
                "GET"
        );

        Response<Model> response = new Response<>();
        new Thread(() -> {
            Request req = new Request(path, new JSONObject());
            Request.GoogleApiResponse res = req.send();
            if (res.error()) {
                response.completeError(new Response.Error(res.errorCode(), new JSONObject(res.body())));
            } else {
                response.complete(ModelImpl.fromJson(new JSONObject(res.body())));
            }
        }).start();
        return response;
    }

    @Override
    @CheckReturnValue
    public Response<Integer> countMessageTokens(Model model, MessagePrompt prompt) {
        Request.Path path = new Request.Path(
                BASE_URL + "/" + API_VERSION + "/%s:countMessageTokens?key=%s",
                new String[]{model.getName(), key},
                "POST"
        );

        Response<Integer> response = new Response<>();
        new Thread(() -> {
            Request req = new Request(path, new JSONObject().put("prompt", prompt.toJson()));
            Request.GoogleApiResponse res = req.send();
            if (res.error()) {
                response.completeError(new Response.Error(res.errorCode(), new JSONObject(res.body())));
            } else {
                response.complete(new JSONObject(res.body()).getInt("tokenCount"));
            }
        }).start();
        return response;
    }
}
