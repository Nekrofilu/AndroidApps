package com.example.filu.deckofcards;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public class DeckPresenter implements IDeckPresenter {
    IDeckView view;
    IDeckRepository repo;
    Context ctx;

    DeckPresenter(Context ctx, IDeckView view, IDeckRepository repo) {
        this.ctx = ctx;
        this.view = view;
        this.repo = repo;
        repo.setPresenter(this);
    }

    @Override
    public void loadCards(int numDecks) {
        view.showWaitingScreen();
        repo.startGettingCards(numDecks);
    }

    public void receiveCards(List<Card> cards) {
        Collections.sort(cards);
        extractImagesAndSendToView(cards);
        extractMessageAndSendToView(cards);
    }

    private void extractImagesAndSendToView(List<Card> cards) {
        List<Drawable> cardImages = new ArrayList<>();
        for (Card card : cards) {
            cardImages.add(card.getImage());
        }
        view.showCards(cardImages);
    }

    private void extractMessageAndSendToView(List<Card> cards) {
        String message = "";

        if (areMinThreeSameColors(cards)) {
            message += ctx.getResources().getString(R.string.colors) + "\n";
        }

        if (areMinThreeCardsLongStairs(cards)) {
            message += ctx.getResources().getString(R.string.stairs) + "\n";
        }

        if (areMinThreeFigures(cards)) {
            message += ctx.getResources().getString(R.string.figures) + "\n";
        }

        if (areMinThreeSameValues(cards)) {
            message += ctx.getResources().getString(R.string.twins) + "\n";
        }

        if (message.equals("")) {
            message = ctx.getResources().getString(R.string.no_known_combinations);
        }
        view.showMessage(message);
    }

    private boolean areMinThreeFigures(List<Card> cards) {
        int numFigures = 0;
        for (Card card : cards) {
            if (card.getValue().equals("ACE") || card.getValue().equals("JACK") || card.getValue().equals("QUEEN") || card.getValue().equals("KING")) {
                numFigures += 1;
            }
        }
        boolean areMinThreeFigures = (numFigures >= 3);
        return areMinThreeFigures;
    }

    private boolean areMinThreeSameColors(List<Card> cards) {
        boolean areMinThreeSameColors = false;
        int[] colorsOccurances = new int[4];
        for (Card card : cards) {
            switch (card.getSuit()) {
                case "SPADES":
                    colorsOccurances[0] = colorsOccurances[0] + 1;
                    break;
                case "CLUBS":
                    colorsOccurances[1] = colorsOccurances[1] + 1;
                    break;
                case "DIAMONDS":
                    colorsOccurances[2] = colorsOccurances[2] + 1;
                    break;
                case "HEARTS":
                    colorsOccurances[3] = colorsOccurances[3] + 1;
                    break;
            }
        }
        for (int i = 0; i < 4 && !areMinThreeSameColors; i++) {
            if (colorsOccurances[i] >= 3) {
                areMinThreeSameColors = true;
            }
        }
        return areMinThreeSameColors;
    }

    private boolean areMinThreeCardsLongStairs(List<Card> cards) {
        boolean areMinThreeCardsLongStairs = false;
        int[] valuesOccurances = countCardValuesOccurances(cards);

        for (int i = 0; i < valuesOccurances.length && !areMinThreeCardsLongStairs; i++) {
            if (i < valuesOccurances.length - 3) {
                if (valuesOccurances[i] != 0 && valuesOccurances[i + 1] != 0 && valuesOccurances[i + 2] != 0) {
                    areMinThreeCardsLongStairs = true;
                }
            }
        }
        return areMinThreeCardsLongStairs;
    }

    private boolean areMinThreeSameValues(List<Card> cards) {
        boolean areMinThreeSameValues = false;
        int[] valuesOccurances = countCardValuesOccurances(cards);

        for (int i = 0; i < valuesOccurances.length && !areMinThreeSameValues; i++) {
            if(valuesOccurances[i] >= 3) {
                areMinThreeSameValues = true;
            }
        }
        return areMinThreeSameValues;
    }

    private int[] countCardValuesOccurances(List<Card> cards) {
        int[] valuesOccurances = new int[13];
        for (Card card : cards) {
            switch (card.getValue()) {
                case ACE:
                    valuesOccurances[0] = valuesOccurances[0] + 1;
                    break;
                case TWO:
                    valuesOccurances[1] = valuesOccurances[1] + 1;
                    break;
                case THREE:
                    valuesOccurances[2] = valuesOccurances[2] + 1;
                    break;
                case FOUR:
                    valuesOccurances[3] = valuesOccurances[3] + 1;
                    break;
                case FIVE:
                    valuesOccurances[4] = valuesOccurances[4] + 1;
                    break;
                case SIX:
                    valuesOccurances[5] = valuesOccurances[5] + 1;
                    break;
                case SEVEN:
                    valuesOccurances[6] = valuesOccurances[6] + 1;
                    break;
                case EIGHT:
                    valuesOccurances[7] = valuesOccurances[7] + 1;
                    break;
                case NINE:
                    valuesOccurances[8] = valuesOccurances[8] + 1;
                    break;
                case TEN:
                    valuesOccurances[9] = valuesOccurances[9] + 1;
                    break;
                case JACK:
                    valuesOccurances[10] = valuesOccurances[10] + 1;
                    break;
                case QUEEN:
                    valuesOccurances[11] = valuesOccurances[11] + 1;
                    break;
                case KING:
                    valuesOccurances[12] = valuesOccurances[12] + 1;
                    break;
                case NULL:
                    break;
            }
        }
        return valuesOccurances;
    }
}
