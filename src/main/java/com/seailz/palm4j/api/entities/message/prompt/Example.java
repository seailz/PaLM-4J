package com.seailz.palm4j.api.entities.message.prompt;

import com.seailz.palm4j.api.entities.message.Message;
import com.seailz.palm4j.internal.entities.message.prompt.ExampleImpl;
import org.json.JSONObject;

/**
 * An input/output example used to instruct the model.
 * It demonstrates how the model should respond or format its response.
 *
 * @author Seailz
 * @since 1.0.0
 */
public interface Example {

    /**
     * An example of an input {@link Message} from the user.
     */
    Message getInput();
    Example setInput(Message input);

    /**
     * An example of what the model should output given the input.
     * @return
     */
    Message getOutput();
    Example setOutput(Message output);

    JSONObject toJson();

    static Example newExample() {
        return new ExampleImpl();
    }

}
