package com.example.filu.simplediceapp;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by filu on 30.04.17.
 */

public interface IDieSidesView {

    void showDieSides(List<Drawable> dieSides);
    void showRollResult(Drawable result);

}
