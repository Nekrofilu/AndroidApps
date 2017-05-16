package com.example.filu.deckofcards;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.numDecksBar)
    SeekBar numDecksBar;

    @BindView(R.id.numDecksText)
    TextView numDecksText;

    @OnClick(R.id.drawCardsBtn)
    void onLosujClicked(View view) {
        Intent drawCardsIntent = new Intent(this, DrawnCardsViewActivity.class);
        drawCardsIntent.putExtra("numDecks", numDecksBar.getProgress() + 1);
        startActivity(drawCardsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        numDecksBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numDecksText.setText(Integer.toString(progress+1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
