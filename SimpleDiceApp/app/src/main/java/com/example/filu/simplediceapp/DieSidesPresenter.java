package com.example.filu.simplediceapp;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by filu on 30.04.17.
 */

public class DieSidesPresenter implements IDieSidesPresenter{
    IDieSidesView view;
    IDieSidesRepository dieSidesRepository;


    public DieSidesPresenter(IDieSidesView view, IDieSidesRepository dieSidesRepository) {
        this.view = view;
        this.dieSidesRepository = dieSidesRepository;
    }

    @Override
    public void loadDieSides() {
        List<Drawable> dieSides = dieSidesRepository.getSides();
        view.showDieSides(dieSides);
    }

    @Override
    public void loadRollResult() {
        List<Drawable> dieSides = dieSidesRepository.getSides();
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        int generated = randomIntGenerator.generate(dieSides.size());
        view.showRollResult(dieSides.get(generated));
    }
}
