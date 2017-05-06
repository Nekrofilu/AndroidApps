package com.example.filu.deckofcards;

/**
 * Created by filu on 02.05.17.
 */

public interface IDeckRepository {

    void startGettingCards(int numDecks);

    void setPresenter(IDeckPresenter deckPresenter);
}
