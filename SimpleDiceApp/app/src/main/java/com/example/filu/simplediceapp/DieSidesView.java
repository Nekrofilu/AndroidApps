package com.example.filu.simplediceapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DieSidesView extends FragmentActivity implements IDieSidesView{
    IDieSidesPresenter presenter;
    IDieSidesRepository repository;

    @BindView(R.id.rerollButton)
    Button rerollBtn;

    @BindView(R.id.currentDieSide)
    ImageView currentSide;


    @OnClick(R.id.rerollButton)
    void onRerollClick(View view) {
        presenter.loadRollResult();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_die_sides);
        ButterKnife.bind(this);
        repository = new DieSidesRepository();
        presenter = new DieSidesPresenter(this, repository);

        presenter.loadDieSides();
        presenter.loadRollResult();
    }

    @Override
    public void showDieSides(List<Drawable> dieSides) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DieSidesFragment fragment = DieSidesFragment.newInstance(2, dieSides);
        transaction.replace(R.id.sample_content_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void showRollResult(Drawable result) {
        currentSide.setImageDrawable(result);
    }


}
