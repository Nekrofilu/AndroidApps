package com.example.filu.deckofcards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class DeckRepository implements IDeckRepository{
    private IDeckPresenter presenter;

    @Override
    public void setPresenter(IDeckPresenter deckPresenter) {
        presenter = deckPresenter;
    }

    @Override
    public void startGettingCards(final int numDecks) {
        new AsyncTask<Void, Void, List<Card>>() {
            DeckOfCardsClient client;
            private String deckId = "";
            private boolean deckNeedsReshuffling = false;
            private final static int NUM_CARDS = 5;

            @Override
            protected void onPostExecute(List<Card> cards) {
                super.onPostExecute(cards);
                presenter.receiveCards(cards);
            }

            @Override
            protected List<Card> doInBackground(Void ... voids) {
                List<Card> result = Collections.EMPTY_LIST;
                client = ServiceGenerator.createService(DeckOfCardsClient.class);
                if (deckId.equals("")) {
                    result = callForNewDeckThenCards(numDecks);
                } else {
                    result = callForCards();
                }
                return result;
            }

            private List<Card> callForNewDeckThenCards(int numDecks) {
                Call<DeckResponseBody> callForDeck = client.getNewDeck(numDecks);
                try {
                    Response<DeckResponseBody> responseDeck = callForDeck.execute();
                    if(responseDeck != null && responseDeck.isSuccessful()) {
                        deckId = responseDeck.body().getDeckId();
                        return callForCards();
                    } else {
                        return Collections.EMPTY_LIST;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return Collections.EMPTY_LIST;
                }
            }

            private List<Card> callForCards() {
                if(deckNeedsReshuffling) {
                   if(!callToReshuffleDeck()) {
                       return Collections.EMPTY_LIST;
                   }
                }
                Call<DrawnCardsResponseBody> callForCards=callForCards = client.getCards(deckId, NUM_CARDS);
                try {
                    Response<DrawnCardsResponseBody> drawnCardsResponse = callForCards.execute();
                    if(drawnCardsResponse.isSuccessful()) {
                        if(drawnCardsResponse.body().getRemaining() < 5) {
                            deckNeedsReshuffling = true;
                        }
                        return callForCardImages(drawnCardsResponse.body().getCards());
                    } else {
                        return Collections.EMPTY_LIST;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    return Collections.EMPTY_LIST;
                }
            }


            private boolean callToReshuffleDeck() {
                Call<DeckResponseBody> callToReshuffle = client.reshuffleDeck(deckId);
                try {
                    Response<DeckResponseBody> reshuffledDeckResponse = callToReshuffle.execute();
                    if(reshuffledDeckResponse != null && reshuffledDeckResponse.isSuccessful()) {
                        deckNeedsReshuffling = false;
                        callForCards();
                        return true;
                    } else {
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            private List<Card> callForCardImages(List<CardResponseBody> cards) {
                List<Card> cardsToReturn = new ArrayList<>();
                for(CardResponseBody card : cards) {
                    Call<ResponseBody> callForImage = client.downloadCardImage(card.getImage());
                    try {
                        Response<ResponseBody> responseImage = callForImage.execute();
                        if(responseImage != null && responseImage.isSuccessful()) {
                            String code = card.getCode();
                            String value = card.getValue();
                            String suit = card.getSuit();
                            Bitmap bitmap = BitmapFactory.decodeStream(responseImage.body().byteStream());
                            cardsToReturn.add(new Card(code, bitmap, suit, value));
                        } else {
                            return Collections.EMPTY_LIST;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Collections.EMPTY_LIST;
                    }
                }
                return cardsToReturn;
            }
        }.execute();
    }
}
