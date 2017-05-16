package com.example.filu.deckofcards;

//import static com.example.filu.deckofcards.Card.CardValue.ACE;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import static com.example.filu.deckofcards.Card.CardValue.ACE;

public class Card implements Comparable<Card> {

    private String code;
    private Bitmap image;
    private String suit;
    private CardValue value;

    Card(String newCode, Bitmap newImage, String newSuit, String newValue) {
        code = newCode;
        image = newImage;
        suit = newSuit;
        value = getEnumCardValueOfString(newValue);
    }

    public Bitmap getImage() {
        return image;
    }

    public CardValue getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getCode() {
        return code;
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
