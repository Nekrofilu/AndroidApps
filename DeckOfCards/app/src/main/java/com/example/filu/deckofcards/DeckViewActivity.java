package com.example.filu.deckofcards;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DeckViewActivity extends AppCompatActivity implements IDeckView, View.OnClickListener {
    IDeckPresenter presenter;
    int numDecks;

    List<Drawable> currentCards;
    String currentMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_waiting);

        IDeckRepository repo = new DeckRepository(getApplicationContext());
        presenter = new DeckPresenter(getApplicationContext(), this, repo);
        numDecks = (int) getIntent().getExtras().get("numDecks");
        presenter.loadCards(numDecks);
    }

    @Override
    public void showCards(List<Drawable> cards) {
        currentCards = cards;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DisplayDrawnCardsFragment fragment = null;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragment = DisplayDrawnCardsFragment.newInstance(5, currentCards);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            fragment = DisplayDrawnCardsFragment.newInstance(3, currentCards);
        }
        transaction.replace(R.id.placeholder_fragment, fragment);
        transaction.commit();
        setContentView(R.layout.activity_deck_view);
    }

    @Override
    public void showWaitingScreen() {
        setContentView(R.layout.layout_waiting);
    }

    @Override
    public void showMessage(String message) {
        currentMessage = message;
        TextView cardsInfo = (TextView) findViewById(R.id.cardsInfoText);
        cardsInfo.setText(message);
    }

    @Override
    public void onClick(View v) {
        presenter.loadCards(numDecks);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DisplayDrawnCardsFragment fragment = null;
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragment = DisplayDrawnCardsFragment.newInstance(5, currentCards);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            fragment = DisplayDrawnCardsFragment.newInstance(3, currentCards);
        }
        transaction.replace(R.id.placeholder_fragment, fragment);
        transaction.commit();
        setContentView(R.layout.activity_deck_view);
        TextView cardsInfo = (TextView) findViewById(R.id.cardsInfoText);
        cardsInfo.setText(currentMessage);
    }
}

