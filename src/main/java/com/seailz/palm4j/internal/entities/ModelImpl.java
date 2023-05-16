package com.seailz.palm4j.internal.entities;

import com.seailz.palm4j.api.entities.Model;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import org.json.JSONObject;

public class ModelImpl implements Model {
    private String name;
    private String baseModelId;
    private String version;
    private String displayName;
    private String description;
    private int inputTokenLimit;
    private int outputTokenLimit;
    private String[] supportedGenerationMethods;
    private double defaultTemperature;
    private double defaultTopP;
    private double defaultTopK;

    public ModelImpl(String name, String baseModelId, String version, String displayName, String description,
                     int inputTokenLimit, int outputTokenLimit, String[] supportedGenerationMethods,
                     double defaultTemperature, double defaultTopP, double defaultTopK) {
        this.name = name;
        this.baseModelId = baseModelId;
        this.version = version;
        this.displayName = displayName;
        this.description = description;
        this.inputTokenLimit = inputTokenLimit;
        this.outputTokenLimit = outputTokenLimit;
        this.supportedGenerationMethods = supportedGenerationMethods;
        this.defaultTemperature = defaultTemperature;
        this.defaultTopP = defaultTopP;
        this.defaultTopK = defaultTopK;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBaseModelId() {
        return baseModelId;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getInputTokenLimit() {
        return inputTokenLimit;
    }

    @Override
    public int getOutputTokenLimit() {
        return outputTokenLimit;
    }

    @Override
    public String[] getSupportedGenerationMethods() {
        return supportedGenerationMethods;
    }

    @Override
    public double getDefaultTemperature() {
        return defaultTemperature;
    }

    @Override
    public double getDefaultTopP() {
        return defaultTopP;
    }

    @Override
    public double getDefaultTopK() {
        return defaultTopK;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("baseModelId", baseModelId);
        jsonObject.put("version", version);
        jsonObject.put("displayName", displayName);
        jsonObject.put("description", description);
        jsonObject.put("inputTokenLimit", inputTokenLimit);
        jsonObject.put("outputTokenLimit", outputTokenLimit);
        jsonObject.put("supportedGenerationMethods", supportedGenerationMethods);
        jsonObject.put("temperature", defaultTemperature);
        jsonObject.put("topP", defaultTopP);
        jsonObject.put("topK", defaultTopK);
        return jsonObject;
    }

    @Contract("_ -> new")
    public static @NotNull ModelImpl fromJson(@NotNull JSONObject jsonObject) {
        JSONArray supportedGenerationMethods = jsonObject.getJSONArray("supportedGenerationMethods");
        String[] supportedGenerationMethodsArray = new String[supportedGenerationMethods.length()];
        for (int i = 0; i < supportedGenerationMethods.length(); i++) {
            supportedGenerationMethodsArray[i] = supportedGenerationMethods.getString(i);
        }
        return new ModelImpl(
                jsonObject.getString("name"),
                jsonObject.has("baseModelId") ? jsonObject.getString("baseModelId") : null,
                jsonObject.has("version") ? jsonObject.getString("version") : null,
                jsonObject.has("displayName") ? jsonObject.getString("displayName") : null,
                jsonObject.has("description") ? jsonObject.getString("description") : null,
                jsonObject.has("inputTokenLimit") ? jsonObject.getInt("inputTokenLimit") : 0,
                jsonObject.has("outputTokenLimit") ? jsonObject.getInt("outputTokenLimit") : 0,
                supportedGenerationMethodsArray,
                jsonObject.has("temperature") ? jsonObject.getDouble("temperature") : 0,
                jsonObject.has("topP") ? jsonObject.getDouble("topP") : 0,
                jsonObject.has("topK") ? jsonObject.getDouble("topK") : 0
        );
    }
}
