package com.seailz.palm4j.api.entities.message;

import com.seailz.palm4j.internal.entities.message.MessageImpl;
import org.json.JSONObject;

/**
 * The base unit of structured text.
 * <br>A <code>Message</code> includes a <code>author</code> and the <code>content</code> of the <code>Message</code>.
 *
 * @author Seailz
 * @since 1.0.0
 */
public interface Message {

    /**
     * Optional. The author of this Message.
     * <br>This serves as a key for tagging the content of this Message when it is fed to the model as text.
     * <br>The author can be any alphanumeric string.
     */
    String getAuthor();
    Message setAuthor(String author);

    /**
     * The content of this Message.
     */
    String getContent();
    Message setContent(String content);

    JSONObject toJson();

    static Message newMessage() {
        return new MessageImpl();
    }

    static Message fromJson(JSONObject json) {
        return new MessageImpl()
                .setAuthor(json.has("author") ? json.optString("author") : null)
                .setContent(json.has("content") ? json.optString("content") : null);
    }

}
