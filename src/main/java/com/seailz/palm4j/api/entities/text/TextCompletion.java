package com.seailz.palm4j.api.entities.text;

import com.seailz.palm4j.api.entities.filter.SafetySetting;
import com.seailz.palm4j.api.entities.message.CitationSource;
import com.seailz.palm4j.api.entities.safety.SafetyRating;
import com.seailz.palm4j.internal.entities.safety.SafetyRatingImpl;
import com.seailz.palm4j.internal.entities.text.TextCompletionImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Output text returned from a model.
 * @author seailz
 * @since 1.0.0
 */
public interface TextCompletion {

    /**
     * Output only. The generated text returned from the model.
     */
    String getOutput();

    /**
     * Ratings for the safety of a response.
     * There is at most one rating per category.
     */
    List<SafetyRating> getSafetyRatings();

    /**
     * Output only. Citation information for model-generated output in this TextCompletion.
     * This field may be populated with attribution information for any text included in the output.
     */
    List<CitationSource> getCitationSources();

    static TextCompletion fromJson(JSONObject json) {
        TextCompletionImpl textCompletion = new TextCompletionImpl();
        textCompletion.setOutput(json.getString("output"));

        List<SafetyRating> ratings = new ArrayList<>();
        if (json.has("safetyRatings")) {
            JSONArray safetyRatings = json.getJSONArray("safetyRatings");
            for (Object safetyRating : safetyRatings) {
                ratings.add(new SafetyRatingImpl().setCategory(SafetySetting.HarmCategory.valueOf(((JSONObject) safetyRating).getString("category")))
                        .setHarmProbability(SafetyRating.HarmProbability.valueOf(((JSONObject) safetyRating).getString("probability"))));
            }
        }

        List<CitationSource> citationSources = new ArrayList<>();
        if (json.has("citationMetadata")) {
            JSONArray citationSourcesJson = json.getJSONObject("citationMetadata").getJSONArray("citationSources");
            for (Object citationSource : citationSourcesJson) {
                citationSources.add(CitationSource.fromJson((JSONObject) citationSource));
            }
        }

        textCompletion.setSafetyRatings(ratings);
        textCompletion.setCitationSources(citationSources);
        return textCompletion;
    }

}
