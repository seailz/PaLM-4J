package com.seailz.palm4j.internal.entities.text;

import com.seailz.palm4j.api.entities.message.CitationSource;
import com.seailz.palm4j.api.entities.safety.SafetyRating;
import com.seailz.palm4j.api.entities.text.TextCompletion;

import java.util.List;

public class TextCompletionImpl implements TextCompletion {
    private String output;
    private List<SafetyRating> safetyRatings;
    private List<CitationSource> citationSources;

    @Override
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public List<SafetyRating> getSafetyRatings() {
        return safetyRatings;
    }

    public void setSafetyRatings(List<SafetyRating> safetyRatings) {
        this.safetyRatings = safetyRatings;
    }

    @Override
    public List<CitationSource> getCitationSources() {
        return citationSources;
    }

    public void setCitationSources(List<CitationSource> citationSources) {
        this.citationSources = citationSources;
    }
}