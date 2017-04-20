package com.example.filu.swimz3cz2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import static com.example.filu.swimz3cz2.R.id.tekstNaglowkaSpinner;

public class Ustawienia extends AppCompatActivity implements View.OnClickListener {
    String[] koloryTla = {"Bialy", "Niebieski", "Szary"};
    public static String[] tekstyNaglowka = {"Aplikacja", "SWIMZ3CZ2", "Naglowek"};
    public static String[] tekstyPowitania = {"Witaj!", "Czesc!", "Hej!"};
    String[] koloryPrzyciskow = {"Czerwony", "Zielony"};
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(MainActivity.APP_PREFERENCES, MODE_PRIVATE);
        setContentView(R.layout.activity_ustawienia);
        ustawWidoki((ConstraintLayout) findViewById(R.id.ustawieniaLayout));


        SeekBar kolorTlaBar = (SeekBar) findViewById(R.id.kolorTlaBar);
        kolorTlaBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float[] hsvColor = {0, 1, 1};
                hsvColor[0] = 360f * progress / seekBar.getMax();
                ConstraintLayout thisLayout = (ConstraintLayout) findViewById(R.id.ustawieniaLayout);
                thisLayout.setBackgroundColor(Color.HSVToColor(hsvColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Spinner tekstNaglowka = (Spinner) findViewById(tekstNaglowkaSpinner);
        if (tekstNaglowka != null) {
            ArrayAdapter<String> adapterTekstNaglowka = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tekstyNaglowka);
            adapterTekstNaglowka.setDropDownViewResource(android.R.layout.simple_spinner_item);
            tekstNaglowka.setAdapter(adapterTekstNaglowka);
        }

        Spinner tekstPowitania = (Spinner) findViewById(R.id.tekstPowitaniaSpinner);
        if(tekstPowitania != null) {
            ArrayAdapter<String> adapterTekstPowitania = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tekstyPowitania);
            adapterTekstPowitania.setDropDownViewResource(android.R.layout.simple_spinner_item);
            tekstPowitania.setAdapter(adapterTekstPowitania);
        }

        Button zapiszButton = (Button) findViewById(R.id.zapiszUstawieniaButton);
        zapiszButton.setOnClickListener(this);
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
                Button przycisk = (Button) findViewById(R.id.zapiszUstawieniaButton);
                przycisk.setBackgroundColor(Color.GRAY);
                break;
            case "Zielony":
                przycisk = (Button) findViewById(R.id.zapiszUstawieniaButton);
                przycisk.setBackgroundColor(Color.GREEN);
                break;
            case "Czerwony":
                przycisk = (Button) findViewById(R.id.zapiszUstawieniaButton);
                przycisk.setBackgroundColor(Color.RED);
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = settings.edit();
        SeekBar kolorTla = (SeekBar) findViewById(R.id.kolorTlaBar);
        Spinner tekstNaglowka= (Spinner) findViewById(R.id.tekstNaglowkaSpinner);
        RadioGroup koloryPrzyciskow = (RadioGroup) findViewById(R.id.koloryPrzyciskowGrupa);
        RadioButton kolorPrzycisku = (RadioButton) findViewById(koloryPrzyciskow.getCheckedRadioButtonId());

        Spinner tekstPowitania = (Spinner) findViewById(R.id.tekstPowitaniaSpinner);

        if(settings.getFloat("kolorTla", -1f) != -1f) {
            editor.remove("kolorTla");
            float wartosc = kolorTla.getProgress()*360f/kolorTla.getMax();
            editor.putFloat("kolorTla", wartosc);
        } else {
            editor.putFloat("kolorTla", kolorTla.getProgress()*360f/kolorTla.getMax());
        }

        if(settings.getInt("tekstNaglowka", -1) != -1) {
            editor.remove("tekstNaglowka");
            editor.putInt("tekstNaglowka", tekstNaglowka.getSelectedItemPosition());
        } else {
            editor.putInt("tekstNaglowka", tekstNaglowka.getSelectedItemPosition());
        }

        if(!settings.getString("kolorPrzyciskow", "brak").equals("brak")) {
            editor.remove("kolorPrzyciskow");
            editor.putString("kolorPrzyciskow",kolorPrzycisku.getText().toString());
        } else {
            editor.putString("kolorPrzyciskow", kolorPrzycisku.getText().toString());
        }


        if(settings.getInt("tekstPowitania", -1) != -1) {
            editor.remove("tekstPowitania");
            editor.putInt("tekstPowitania", tekstPowitania.getSelectedItemPosition());
        } else {
            editor.putInt("tekstPowitania", tekstPowitania.getSelectedItemPosition());
        }
        editor.apply();
        recreate();
    }
}
