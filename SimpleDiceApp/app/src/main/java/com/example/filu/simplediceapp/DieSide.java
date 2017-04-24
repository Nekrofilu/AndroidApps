package com.example.filu.simplediceapp;

/**
 * Created by filu on 20.04.17.
 */

public class DieSide {
    private int sourceId;

    DieSide(int sourceId) {
        this.sourceId = sourceId;
    }

    int getSourceId() {
        return sourceId;
    }

    @Override
    public boolean equals(Object obj) {
        return sourceId == ((DieSide) obj).getSourceId();
    }
}
