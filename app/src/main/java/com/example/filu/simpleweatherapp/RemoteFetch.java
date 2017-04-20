package com.example.filu.simpleweatherapp;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by filu on 12.04.17.
 */

public class RemoteFetch {
    private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static JSONObject getJSON(Context context, String city) {
        JSONObject data = null;
        try {

            String string_url = OPEN_WEATHER_MAP_API + city + "&units=metric" + "&APPID=" + "c11a4dd7a2fb76b4fdd1b133a7f9d3dd";
            URL url = new URL(string_url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader in = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(in);

            StringBuffer json = new StringBuffer(1024);

            String tmp="";
            while((tmp=reader.readLine())!=null) {
                json.append(tmp).append("\n");
            }

            reader.close();

            data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){
                return null;
            }
        } catch (MalformedURLException e) { //during creating url
            e.printStackTrace();
        } catch (IOException e) { //during url opening connection
            e.printStackTrace();
        } catch (JSONException e) { // during JSONObject creation
            e.printStackTrace();
        }
        return data;
    }
}
