package com.seailz.palm4j.api.entities;

import org.json.JSONObject;

/**
 * Information about a Generative Language Model.
 * @author Seailz
 * @since 1.0.0
 */
public interface Model {

    /**
     * Returns the name of the model.
     * <br>Format: models/{model} with a {model} naming convention of:
     * <ul>
     *     <li>{baseModelId}-{version}</li>
     * </ul>
     * Examples:
     * <ul>
     *     <li>models/chat-bison-001</li>
     * </ul>
     */
    String getName();

    /**
     * Returns the name of the base model.
     * <br>Examples:
     * <li>chat-bison</li>
     */
    String getBaseModelId();

    /**
     * Returns the version number of the model.
     * <br>This represents the major version.
     */
    String getVersion();

    /**
     * Returns the human-readable name of the model.
     * <br>Examples:
     * <ul><li>Chat Bison</li></ul>
     * This name can be up to 128 characters long and can consist of any UTF-8 characters.
     */
    String getDisplayName();

    /**
     * Returns a short description of the model.
     */
    String getDescription();

    /**
     * Returns the maximum number of input tokens allowed for this model.
     */
    int getInputTokenLimit();

    /**
     * Returns the maximum number of output tokens available for this model.
     */
    int getOutputTokenLimit();

    /**
     * Returns the model's support generation methods.
     * <br>The method names are defined as Pascal case strings, such as <code>generateMessage</code> which correspond to API methods.
     */
    String[] getSupportedGenerationMethods();

    /**
     * Controls the randomness of the output.
     *
     * Values can range over [0.0,1.0], inclusive. A value closer to 1.0 will produce responses that are more varied, while a value closer to 0.0 will typically result in less surprising responses from the model. This value specifies default to be used by the backend while making the call to the model.
     */
    double getDefaultTemperature();

    /**
     * For Nucleus sampling.
     *
     * Nucleus sampling considers the smallest set of tokens whose probability sum is at least topP. This value specifies default to be used by the backend while making the call to the model.
     */
    double getDefaultTopP();

    /**
     * For Top-k sampling.
     *
     * Top-k sampling considers the set of topK most probable tokens. This value specifies default to be used by the backend while making the call to the model.
     */
    double getDefaultTopK();

    /**
     * Returns this as a JSON object.
     */
    JSONObject toJson();


}
