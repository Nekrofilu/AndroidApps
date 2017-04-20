package com.example.filu.swimz3cz2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.filu.swimz3cz2.MainActivity.APP_PREFERENCES;
import static com.example.filu.swimz3cz2.R.id.listaButton;
import static com.example.filu.swimz3cz2.Ustawienia.tekstyNaglowka;

public class Wprowadzanie extends AppCompatActivity {
    public static final String FILENAME = "wprowadzanie";
    SharedPreferences settings;
    public static String[] jablka = {"Tak", "Nie", "Nie wiem"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wprowadzanie);
        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        ustawWidoki((ConstraintLayout) findViewById(R.id.wprowadzanieLayout));

        SeekBar seekBar = (SeekBar) findViewById(R.id.pensjaBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView seekBarProgressView = (TextView) findViewById(R.id.progressBarText);
                seekBarProgressView.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        Spinner lubiJablka = (Spinner) findViewById(R.id.jablkaSpinner);
        ArrayAdapter<String> adapterLubiJablka = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jablka);
        adapterLubiJablka.setDropDownViewResource(android.R.layout.simple_spinner_item);
        lubiJablka.setAdapter(adapterLubiJablka);

        Button zapisz = (Button) findViewById(R.id.zapiszButton);
        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nazwisko = (EditText) findViewById(R.id.nazwiskoBox);
                SeekBar pensja = (SeekBar) findViewById(R.id.pensjaBar);
                RadioGroup ulubionyKolorGrupa = (RadioGroup) findViewById(R.id.ulubionyKolorGrupa);
                RadioButton ulubionyKolor = (RadioButton) findViewById(ulubionyKolorGrupa.getCheckedRadioButtonId());
                Spinner lubiJablka = (Spinner) findViewById(R.id.jablkaSpinner);

                String dane_nazwisko = nazwisko.getText() + "&";
                String dane_pensja = Integer.toString(pensja.getProgress()) + "&";
                String dane_ulubiony_kolor = ulubionyKolor.getText().toString() + "&";
                String dane_opinia_jablka = jablka[lubiJablka.getSelectedItemPosition()] + "&";

                if(!dane_nazwisko.equals("")) {
                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        byte[] bytes = dane_nazwisko.getBytes();
                        fos.write(dane_nazwisko.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.write(dane_pensja.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.write(dane_ulubiony_kolor.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.write(dane_opinia_jablka.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        final Intent prostaIntent = new Intent(this, PrezentacjaSimple.class);
        Button prostaButton = (Button) findViewById(R.id.listaButton);
        prostaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(prostaIntent);
            }
        });

        final Intent dostosowanaIntent = new Intent(this, PrezentacjaWlasnyAdapter.class);
        Button dostosowanaButton = (Button) findViewById(R.id.dostosowanaButton);
        dostosowanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(dostosowanaIntent);
            }
        });
    }

    private void ustawWidoki(ConstraintLayout thisLayout) {
        float kolorTla = settings.getFloat("kolorTla", -1f);
        int tekstNaglowka = settings.getInt("tekstNaglowka", -1);
        String kolorPrzyciskow = settings.getString("kolorPrzyciskow", "brak");

        if(kolorTla != -1f) {
            float[] hsvColor = {kolorTla, 1, 1};
            thisLayout.setBackgroundColor(Color.HSVToColor(hsvColor));
        }

        if(tekstNaglowka != -1) {
            this.setTitle(tekstyNaglowka[tekstNaglowka]);
        }

        switch(kolorPrzyciskow) {
            case "Szary":
                Button przycisk1 = (Button) findViewById(R.id.zapiszButton);
                przycisk1.setBackgroundColor(Color.GRAY);
                Button przycisk2 = (Button) findViewById(listaButton);
                przycisk2.setBackgroundColor(Color.GRAY);
                Button przycisk3 = (Button) findViewById(R.id.dostosowanaButton);
                przycisk3.setBackgroundColor(Color.GRAY);
                break;
            case "Zielony":
                przycisk1 = (Button) findViewById(R.id.zapiszButton);
                przycisk1.setBackgroundColor(Color.GREEN);
                przycisk2 = (Button) findViewById(listaButton);
                przycisk2.setBackgroundColor(Color.GREEN);
                przycisk3 = (Button) findViewById(R.id.dostosowanaButton);
                przycisk3.setBackgroundColor(Color.GREEN);
                break;
            case "Czerwony":
                przycisk1 = (Button) findViewById(R.id.zapiszButton);
                przycisk1.setBackgroundColor(Color.RED);
                przycisk2 = (Button) findViewById(listaButton);
                przycisk2.setBackgroundColor(Color.RED);
                przycisk3 = (Button) findViewById(R.id.dostosowanaButton);
                przycisk3.setBackgroundColor(Color.RED);
                break;
            default:
        }
    }
}
