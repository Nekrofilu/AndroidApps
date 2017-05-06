package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public interface IDeckView {

    void showCards(List<Drawable> cards);

    void showWaitingScreen();

    void showMessage(String message);
}
