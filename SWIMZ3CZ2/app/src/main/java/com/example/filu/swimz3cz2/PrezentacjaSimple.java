package com.example.filu.swimz3cz2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.filu.swimz3cz2.MainActivity.APP_PREFERENCES;
import static com.example.filu.swimz3cz2.Ustawienia.tekstyNaglowka;

public class PrezentacjaSimple extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<String> nazwiska;
    ArrayList<String> pensje;
    ArrayList<String> ulubioneKolory;
    ArrayList<String> lubiaJablka;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prezentacja_simple);
        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        ustawWidoki((ConstraintLayout) findViewById(R.id.prostaLayout));

        String[] dataSeparated = odzyskajDane();
        nazwiska = new ArrayList<>();
        pensje = new ArrayList<>();
        ulubioneKolory = new ArrayList<>();
        lubiaJablka = new ArrayList<>();
        for(int i = 0; i < dataSeparated.length; i+=4) {
            nazwiska.add(dataSeparated[i]);
            pensje.add(dataSeparated[i+1]);
            ulubioneKolory.add(dataSeparated[i+2]);
            lubiaJablka.add(dataSeparated[i+3]);
        }
        String[] nazwiskaArray = nazwiska.toArray(new String[0]);
        ArrayAdapter<String> adapterNazwisk = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nazwiskaArray);
        ListView lista = (ListView) findViewById(R.id.listaProsta);
        lista.setOnItemClickListener(this);
        lista.setAdapter(adapterNazwisk);
    }

    private void ustawWidoki(ConstraintLayout thisLayout) {
        float kolorTla = settings.getFloat("kolorTla", -1f);
        int tekstNaglowka = settings.getInt("tekstNaglowka", -1);
        if(kolorTla != -1f) {
            float[] hsvColor = {kolorTla, 1, 1};
            thisLayout.setBackgroundColor(Color.HSVToColor(hsvColor));
        }
        if(tekstNaglowka != -1) {
            this.setTitle(tekstyNaglowka[tekstNaglowka]);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = "";
        text += "Nazwisko: " + nazwiska.get(position) + "\n\n";
        text += "Pensja: " + pensje.get(position) + "\n\n";
        text += "Ulubiony kolor: " + ulubioneKolory.get(position) + "\n\n";
        text += "Lubie jablka: " + lubiaJablka.get(position);

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    private String[] odzyskajDane() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(Wprowadzanie.FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean reachedEnd = false;
        ArrayList<Character> data = new ArrayList<>();
        while(!reachedEnd) {
            byte readByte = -1;
            try {
                readByte = (byte) fis.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(readByte == -1) {
                reachedEnd = true;
            } else {
                data.add((char) readByte);
            }
        }

        String dataString = "";
        for(Character c : data) {
            dataString += c;
        }
        return dataString.split("&");
    }
}
