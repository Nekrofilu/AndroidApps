package com.example.filu.simplediceapp;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filu on 30.04.17.
 */

public class MockDatabase {
    List<Drawable> images;

    public MockDatabase() {
        images = new ArrayList<>();
        images.add(Drawable.createFromPath("/home/filu/Desktop/Repo/SimpleDiceApp/app/src/main/res/drawable/die1.png"));
        images.add(Drawable.createFromPath("/home/filu/Desktop/Repo/SimpleDiceApp/app/src/main/res/drawable/die2.png"));
        images.add(Drawable.createFromPath("/home/filu/Desktop/Repo/SimpleDiceApp/app/src/main/res/drawable/die3.png"));
        images.add(Drawable.createFromPath("/home/filu/Desktop/Repo/SimpleDiceApp/app/src/main/res/drawable/die4.png"));
        images.add(Drawable.createFromPath("/home/filu/Desktop/Repo/SimpleDiceApp/app/src/main/res/drawable/die5.png"));
        images.add(Drawable.createFromPath("/home/filu/Desktop/Repo/SimpleDiceApp/app/src/main/res/drawable/die6.png"));
    }

    List<Drawable> mockQuery() {
        return images;
    }
}
