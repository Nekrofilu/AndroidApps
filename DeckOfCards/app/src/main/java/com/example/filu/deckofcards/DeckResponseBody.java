package com.example.filu.deckofcards;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "shuffled",
        "remaining",
        "deck_id",
        "success"
})
public class DeckResponseBody {

    @JsonProperty("shuffled")
    private Boolean shuffled;
    @JsonProperty("remaining")
    private Integer remaining;
    @JsonProperty("deck_id")
    private String deckId;
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("shuffled")
    public Boolean getShuffled() {
        return shuffled;
    }

    @JsonProperty("shuffled")
    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }

    @JsonProperty("remaining")
    public Integer getRemaining() {
        return remaining;
    }

    @JsonProperty("remaining")
    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    @JsonProperty("deck_id")
    public String getDeckId() {
        return deckId;
    }

    @JsonProperty("deck_id")
    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
