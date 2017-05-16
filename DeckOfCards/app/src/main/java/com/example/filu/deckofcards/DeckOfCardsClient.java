package com.example.filu.deckofcards;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface DeckOfCardsClient {

    @GET("new/shuffle/")
    Call<DeckResponseBody> getNewDeck(@Query("deck_count") int deck_count);

    @GET("{deck_id}/draw/")
    Call<DrawnCardsResponseBody> getCards(@Path("deck_id") String deck_id, @Query("count") int count);

    @Streaming
    @GET("{deck_id}/draw/?count=2")
    Call<DrawnCardsResponseBody> get2Cards(@Path("deck_id") String deck_id);

    @GET("{deck_id}/shuffle/")
    Call<DeckResponseBody> reshuffleDeck(@Path("deck_id") String deck_id);

    @GET
    Call<ResponseBody> downloadCardImage(@Url String cardImageUrl);
}
