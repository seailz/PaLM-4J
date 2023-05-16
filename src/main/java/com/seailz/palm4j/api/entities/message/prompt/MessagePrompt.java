package com.seailz.palm4j.api.entities.message.prompt;

import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.internal.entities.message.prompt.MessagePromptImpl;
import org.json.JSONObject;

import java.util.List;

/**
 * A MessagePrompt contains a structured set of fields that provide context for the conversation, examples of user input/model output message pairs that prime the model to respond in different ways, and the conversation history or list of messages representing the alternating turns of the conversation between the user and the model.
 * @author Seailz
 * @since 1.0.0
 */
public interface MessagePrompt {

    /**
     * Optional. Text that should be provided to the model first to ground the response.
     */
    String getContext();
    MessagePrompt setContext(String context);

    /**
     * Optional. A list of examples of user input/model output message pairs that prime the model to respond in different ways.
     */
    List<Example> getExamples();
    MessagePrompt setExamples(List<Example> examples);
    MessagePrompt addExample(Example example);
    MessagePrompt addExamples(Example... examples);

    /**
     * Required. A snapshot of the recent conversation history sorted chronologically. If the total input size exceeds the model's inputTokenLimit the input will be truncated: The oldest items will be dropped from messages.
     */
    List<Message> getMessages();
    MessagePrompt setMessages(List<Message> messages);
    MessagePrompt addMessage(Message message);
    MessagePrompt addMessages(Message... messages);

    JSONObject toJson();

    static MessagePrompt newPrompt() {
        return new MessagePromptImpl();
    }


}
