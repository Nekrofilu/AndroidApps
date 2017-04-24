package com.example.filu.simplediceapp;

/**
 * Created by filu on 20.04.17.
 */

public class DieSide {
    private String source;

    DieSide(String source) {
        this.source = source;
    }

    String getSource() {
        return source;
    }

    @Override
    public boolean equals(Object obj) {
        return getSource().equals(((DieSide) obj).getSource());
    }
}
