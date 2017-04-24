package com.example.filu.simplediceapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.filu.simplediceapp.DieSidesAdapter.ctx;

public class DieSides extends FragmentActivity {
    private static final int DATASET_COUNT = 6;
    static DieSide[] mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_die_sides);
        initDataset();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            DieSidesFragment fragment = new DieSidesFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        Button rerollButton = (Button) findViewById(R.id.rerollButton);
        rerollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomIntGenerator gen = new RandomIntGenerator();
                ImageView currentRolled = (ImageView) findViewById(R.id.currentDieSide);
                currentRolled.setImageDrawable(ContextCompat.getDrawable(ctx.getApplicationContext(), mDataset[gen.generate(mDataset.length)].getSourceId()));
            }
        });
    }

    private void initDataset() {
        mDataset = new DieSide[DATASET_COUNT];
        mDataset[0] = new DieSide(R.drawable.die1);
        mDataset[1] = new DieSide(R.drawable.die2);
        mDataset[2] = new DieSide(R.drawable.die3);
        mDataset[3] = new DieSide(R.drawable.die4);
        mDataset[4] = new DieSide(R.drawable.die5);
        mDataset[5] = new DieSide(R.drawable.die6);
    }

}
