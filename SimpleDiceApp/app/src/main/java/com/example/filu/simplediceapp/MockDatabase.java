package com.example.filu.simplediceapp;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filu on 30.04.17.
 */

public class MockDatabase {
    List<Drawable> images;

    public MockDatabase(Context ctx) {
        images = new ArrayList<>();
        Drawable xxx = ctx.getResources().getDrawable(R.drawable.die1);


        images.add(ctx.getResources().getDrawable(R.drawable.die1));
        images.add(ctx.getResources().getDrawable(R.drawable.die2));
        images.add(ctx.getResources().getDrawable(R.drawable.die3));
        images.add(ctx.getResources().getDrawable(R.drawable.die4));
        images.add(ctx.getResources().getDrawable(R.drawable.die5));
        images.add(ctx.getResources().getDrawable(R.drawable.die6));
    }

    List<Drawable> mockQuery() {
        return images;
    }
}
