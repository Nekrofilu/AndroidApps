package com.example.filu.simplediceapp;

import java.util.Random;

/**
 * Created by filu on 20.04.17.
 */

public class RandomIntGenerator {
    public int generate(int range) {
        Random r = new Random();
        return r.nextInt(range);
    }
}
