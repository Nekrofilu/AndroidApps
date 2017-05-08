package com.example.filu.deckofcards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public class DeckRepository implements IDeckRepository{
    private IDeckPresenter presenter;
    private String deckId = "";
    private boolean deckNeedsReshuffling = false;

    private final static String API_URL = "https://deckofcardsapi.com/api/deck/";

    @Override
    public void startGettingCards(int numDecks) {
        new DrawCardsTask().execute(numDecks);
    }

    @Override
    public void setPresenter(IDeckPresenter deckPresenter) {
        presenter = deckPresenter;
    }

    private class DrawCardsTask extends AsyncTask<Integer, Void, List<Card>> {
        @Override
        protected void onPostExecute(List<Card> cards) {
            super.onPostExecute(cards);
            presenter.receiveCards(cards);
        }

        @Override
        protected List<Card> doInBackground(Integer... params) {
            if(deckId.equals("")) {
                int numDecks = params[0];
                String newDeckId = getNewDeckId(numDecks);
                if(!newDeckId.equals("")) {
                    deckId = newDeckId;
                } else {
                    return Collections.EMPTY_LIST;
                }
            }
            return getCards(deckId);
        }

        private String getNewDeckId(int numDecks) {
            String stringGetNewDeckURL = API_URL + "new/shuffle/?deck_count=" + Integer.toString(numDecks);
            try {
                JSONObject deckJSON = getJSONFromUrl(stringGetNewDeckURL);
                if(deckJSON != null && deckJSON.getBoolean("success")) {
                    return deckJSON.getString("deck_id");
                }
                return "";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        private List<Card> getCards(String deckId) {
            if(deckId != null && !deckId.equals("")) {
                String stringGetCardsUrl = API_URL + deckId + "/draw/?count=5";
                if(deckNeedsReshuffling) {
                    if(!reshuffleDeck(deckId)) {
                        return Collections.EMPTY_LIST;
                    }
                    deckNeedsReshuffling = false;
                }
                try {
                    JSONObject cardsJson = getJSONFromUrl(stringGetCardsUrl);
                    if(cardsJson != null && cardsJson.getBoolean("success")) {
                        if(cardsJson.getInt("remaining") < 5) {
                            deckNeedsReshuffling = true;
                        }
                        List<Card> resultCardList = new ArrayList<>();
                        JSONArray cardsArrayJSON = cardsJson.getJSONArray("cards");
                        for(int i = 0; i < cardsArrayJSON.length(); i++) {
                            JSONObject cardJSON = cardsArrayJSON.getJSONObject(i);
                            Drawable image = drawableFromUrl(cardJSON.getString("image"));
                            if(image != null) {
                                String value = cardJSON.getString("value");
                                String suit = cardJSON.getString("suit");
                                String code = cardJSON.getString("code");
                                Card.CardValue cardValue = Card.getEnumCardValueOfString(value);
                                Card resultCard = new Card(image).setValue(cardValue).setCode(code).setSuit(suit);
                                resultCardList.add(resultCard);
                            }
                        }
                        return resultCardList;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Collections.EMPTY_LIST;
                }
            }
            return Collections.EMPTY_LIST;
        }

        private boolean reshuffleDeck(String deckId) {
            String stringReshuffleDeckUrl = API_URL + deckId + "/shuffle/";

                JSONObject reshuffledDeckJSON = getJSONFromUrl(stringReshuffleDeckUrl);
                if(reshuffledDeckJSON != null) {
                    try {
                        return reshuffledDeckJSON.getBoolean("success");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            return false;
        }


        private Drawable drawableFromUrl(String url) throws IOException {
            Bitmap x;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();

            x = BitmapFactory.decodeStream(input);
            return new BitmapDrawable(x);
        }

        private JSONObject getJSONFromUrl(String stringUrl) {

            try {
                URL reshuffleDeckURL = new URL(stringUrl);
                HttpURLConnection reshuffleDeckURLConnection = (HttpURLConnection) reshuffleDeckURL.openConnection();
                InputStream inputStream = reshuffleDeckURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer stringResponse = new StringBuffer(1024);
                String tmp = "";
                while ((tmp = bufferedReader.readLine()) != null) {
                    stringResponse.append(tmp).append("\n");
                }
                bufferedReader.close();

                return new JSONObject(stringResponse.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
