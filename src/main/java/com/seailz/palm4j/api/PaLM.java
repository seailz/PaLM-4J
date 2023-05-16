package com.seailz.palm4j.api;

import com.seailz.palm4j.api.entities.Model;
import com.seailz.palm4j.api.entities.message.prompt.MessagePrompt;
import com.seailz.palm4j.internal.PaLMImpl;
import com.seailz.palm4j.internal.utils.rest.Response;

import java.util.List;

/**
 * Main interface for the PaLM API.
 * @author Seailz
 * @since 1.0.0
 */
public interface PaLM {

    /**
     * Returns the API key provided by the developer.
     */
    String getKey();

    /**
     * Lists models available through the API.
     */
    Response<List<Model>> getModels();

    /**
     * Given a model name, returns information about the model.
     * @param name The name of the model. The name should match a {@link Model#getName()} returned
     *             by {@link #getModels() getModels()}. Format: <code>models/{model}</code>.
     * @return A {@link Response Response} containing the model information, or an error if one occurred.
     */
    Response<Model> getModel(String name);

    /**
     * Counts the number of tokens in a {@link MessagePrompt MessagePrompt}
     * @param prompt The message prompt to count tokens for.
     */
    Response<Integer> countMessageTokens(Model model, MessagePrompt prompt);

    static PaLM newPaLM(String key) {
        return new PaLMImpl(key);
    }

}
