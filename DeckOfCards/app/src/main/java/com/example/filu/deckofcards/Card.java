package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import static com.example.filu.deckofcards.Card.CardValue.ACE;

/**
 * Created by filu on 02.05.17.
 */

public class Card implements Comparable<Card> {
    private String code;
    private Drawable image;
    private CardValue value;
    private String suit;

    public Card(@NonNull Drawable image) {
        code = "";
        this.image = image;
        value = CardValue.NULL;
        suit = "";
    }

    public String getCode() {
        return String.copyValueOf(code.toCharArray());
    }

    public Drawable getImage() {
        return image;
    }

    public CardValue getValue() {
        return value;
    }

    public String getSuit() {
        return String.copyValueOf(suit.toCharArray());
    }

    Card setValue(CardValue value) {
        if(value != CardValue.NULL) {
            this.value = value;
        }
        return this;
    }

    Card setCode(String code) {
        if(code != null) {
            this.code = code;
        }
        return this;
    }
    Card setSuit(String suit) {
        if(suit != null) {
            this.suit = suit;
        }
        return this;
    }
    Card setImage(Drawable image) {
        if(image != null) {
            this.image = image;
        }
        return this;
    }

    @Override
    public int compareTo(@NonNull Card o) {
        return value.compareTo(o.value);
    }

    public static CardValue getEnumCardValueOfString(String value) {
        switch(value) {
            case "ACE":
                return ACE;
            case "2":
                return CardValue.TWO;
            case "3":
                return CardValue.THREE;
            case "4":
                return CardValue.FOUR;
            case "5":
                return CardValue.FIVE;
            case "6":
                return CardValue.SIX;
            case "7":
                return CardValue.SEVEN;
            case "8":
                return CardValue.EIGHT;
            case "9":
                return CardValue.NINE;
            case "10":
                return CardValue.TEN;
            case "JACK":
                return CardValue.JACK;
            case "QUEEN":
                return CardValue.QUEEN;
            case "KING":
                return CardValue.KING;
            default:
                return CardValue.NULL;
        }
    }

    public enum CardValue {
        NULL, ACE, TWO, THREE, FOUR,
        FIVE, SIX, SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN,
        KING
    }
}
