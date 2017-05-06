package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by filu on 02.05.17.
 */

public class Card implements Comparable<Card> {
    String code;
    Drawable image;
    String value;
    String suit;

    public Card() {
        code = "";
        image = null;
        value = "";
        suit = "";
    }

    Card setValue(String value) {
        this.value = value;
        return this;
    }

    Card setCode(String code) {
        this.code = code;
        return this;
    }
    Card setSuit(String suit) {
        this.suit = suit;
        return this;
    }
    Card setImage(Drawable image) {
        this.image = image;
        return this;
    }

    @Override
    public int compareTo(@NonNull Card o) {
        //these ifs are used beacause in some cases you can't just compare the values eg. QUEEN comes after KING and the result would be wrong
        if(value.equals("KING") || (value.equals("10") && o.value.length()==1) || (o.value.equals("ACE"))) {
            return 1;
        }
        if(o.value.equals("KING") || (o.value.equals("10") && value.length()==1) || (value.equals("ACE"))) {
            return -1;
        }
        return value.compareTo(o.value);
    }
}
