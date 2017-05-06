package com.example.filu.deckofcards;

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

    DeckPresenter(IDeckView view, IDeckRepository repo) {
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
        Collections.sort(cards); //cards are comparable
        extractImagesAndSendToVieW(cards);
        extractMessageAndSendToView(cards);
    }

    private void extractImagesAndSendToVieW(List<Card> cards) {
        List<Drawable> cardImages = new ArrayList<>();
        for(Card card : cards) {
            cardImages.add(card.image);
        }
        view.showCards(cardImages);
    }

    private void extractMessageAndSendToView(List<Card> cards) {
        String message = "";
        boolean[] kfb = areColorsFiguresTwinsStairs(cards);

        if(kfb[0]) {
            message += "kolor\n";
        }

        if(kfb[3]) {
            message += "schodki\n";
        }

        if(kfb[1]) {
            message += "figury\n";
        }

        if(kfb[2]) {
            message += "blizniaki\n";
        }

        if(message.equals("")) {
            message = "Brak charakterystycznych ulozen";
        }
        view.showMessage(message);
    }

    private boolean[] areColorsFiguresTwinsStairs(List<Card> cards) {
        /*init with all false
         index 0 is whether or not there is colors, where colors means at least 3 cards of the same suit
         index 1 is whether or not there are figures, where figures means at least 3 figures
         index 2 is whether or not there are twins, where twins means at least 3 cards of the same value
         index 3 is whether or not there are stairs, where stairs means at least 3 consecutive cards
         */
        boolean[] colorsFiguresTwinsStairs = new boolean[4];

        int[] colorsOccurances = new int[4]; //each elem of table is occurances of one color, doesn't matter which in this case
        int numFigures = 0;
        int[] valuesOccurances = new int[13]; //each elem of table is occurances of one value, doesn't matter which in this case

        for(Card card : cards) {
            switch(card.value) {
                case "ACE":
                    valuesOccurances[0] = valuesOccurances[0]+1;
                    //Ace is a figure
                    numFigures += 1;
                    break;
                case "2":
                    valuesOccurances[1] = valuesOccurances[1]+1;
                    break;
                case "3":
                    valuesOccurances[2] = valuesOccurances[2]+1;
                    break;
                case "4":
                    valuesOccurances[3] = valuesOccurances[3]+1;
                    break;
                case "5":
                    valuesOccurances[4] = valuesOccurances[4]+1;
                    break;
                case "6":
                    valuesOccurances[5] = valuesOccurances[5]+1;
                    break;
                case "7":
                    valuesOccurances[6] = valuesOccurances[6]+1;
                    break;
                case "8":
                    valuesOccurances[7] = valuesOccurances[7]+1;
                    break;
                case "9":
                    valuesOccurances[8] = valuesOccurances[8]+1;
                    break;
                case "10":
                    valuesOccurances[9] = valuesOccurances[9]+1;
                    break;
                case "JACK":
                    valuesOccurances[10] = valuesOccurances[10]+1;
                    numFigures += 1;
                    break;
                case "QUEEN":
                    valuesOccurances[11] = valuesOccurances[11]+1;
                    numFigures += 1;
                    break;
                case "KING":
                    valuesOccurances[12] = valuesOccurances[12]+1;
                    numFigures += 1;
                    break;
            }

            switch(card.suit) {
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

            for(int i = 0; i<valuesOccurances.length; i++) {
                if(i < 4) {
                    if(colorsOccurances[i] >= 3) {
                        colorsFiguresTwinsStairs[0] = true;
                    }
                }
                if(valuesOccurances[i] >= 3) {
                    colorsFiguresTwinsStairs[2] = true;
                }
                if(i < valuesOccurances.length-3) {
                    if(valuesOccurances[i]*valuesOccurances[i+1]*valuesOccurances[i+2] != 0) { // this means that there are at least 3 consecutive cards, none of these 3 occur 0 times
                        colorsFiguresTwinsStairs[3] = true;
                    }
                }
            }

            if(numFigures >= 3) {
                colorsFiguresTwinsStairs[1] = true;
            }
        }
        return colorsFiguresTwinsStairs;
    }

}
