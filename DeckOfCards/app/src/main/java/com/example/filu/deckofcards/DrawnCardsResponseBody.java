package com.example.filu.deckofcards;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "remaining",
        "cards",
        "deck_id",
        "success"
})
public class DrawnCardsResponseBody {

    @JsonProperty("remaining")
    private Integer remaining;
    @JsonProperty("cards")
    private List<CardResponseBody> cards = null;
    @JsonProperty("deck_id")
    private String deckId;
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("remaining")
    public Integer getRemaining() {
        return remaining;
    }

    @JsonProperty("cards")
    public List<CardResponseBody> getCards() {
        return cards;
    }

    @JsonProperty("deck_id")
    public String getDeckId() {
        return deckId;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }
}

