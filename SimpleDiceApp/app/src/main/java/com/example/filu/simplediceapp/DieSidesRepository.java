package com.example.filu.simplediceapp;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by filu on 30.04.17.
 */

public class DieSidesRepository implements IDieSidesRepository{


    private List<Drawable> sides;

    public DieSidesRepository() {
        MockDatabase db = new MockDatabase();
        sides = db.mockQuery();
    }

    @Override
    public List<Drawable> getSides() {
        return sides;
    }
}
