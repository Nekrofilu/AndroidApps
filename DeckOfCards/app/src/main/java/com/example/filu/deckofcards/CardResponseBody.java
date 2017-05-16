package com.example.filu.deckofcards;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by filu on 16.05.17.
 */


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "code",
            "image",
            "suit",
            "value",
            "images"
    })
    public class CardResponseBody {

        @JsonProperty("code")
        private String code;
        @JsonProperty("image")
        private String image;
        @JsonProperty("suit")
        private String suit;
        @JsonProperty("value")
        private String value;
        @JsonProperty("images")
        private ImagesResponseBody images;

        @JsonProperty("code")
        public String getCode() {
            return code;
        }

        @JsonProperty("code")
        public void setCode(String code) {
            this.code = code;
        }

        @JsonProperty("image")
        public String getImage() {
            return image;
        }

        @JsonProperty("image")
        public void setImage(String image) {
            this.image = image;
        }

        @JsonProperty("suit")
        public String getSuit() {
            return suit;
        }

        @JsonProperty("suit")
        public void setSuit(String suit) {
            this.suit = suit;
        }

        @JsonProperty("value")
        public String getValue() {
            return value;
        }

        @JsonProperty("value")
        public void setValue(String value) {
            this.value = value;
        }

        @JsonProperty("images")
        public ImagesResponseBody getImages() {
            return images;
        }

        @JsonProperty("images")
        public void setImages(ImagesResponseBody images) {
            this.images = images;
        }

    }
