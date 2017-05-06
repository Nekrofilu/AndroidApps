package com.example.filu.deckofcards;

import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public interface IDeckPresenter {

    void loadCards(int numDecks);
    void receiveCards(List<Card> cards);
}
