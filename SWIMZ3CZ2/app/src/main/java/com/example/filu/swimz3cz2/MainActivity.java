package com.example.filu.swimz3cz2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.filu.swimz3cz2.Ustawienia.tekstyNaglowka;
import static com.example.filu.swimz3cz2.Ustawienia.tekstyPowitania;

public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "Ustawienia";
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        ustawWidoki((ConstraintLayout) findViewById(R.id.mainLayout));

        final Intent wprowadzanieIntent = new Intent(this, Wprowadzanie.class);
        Button wprowadzanieButton = (Button) findViewById(R.id.wprowadzanieButton);
        wprowadzanieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(wprowadzanieIntent);
            }
        });

        final Intent ustawieniaIntent = new Intent(this, Ustawienia.class);
        Button ustawieniaButton = (Button) findViewById(R.id.ustawieniaButton);
        ustawieniaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ustawieniaIntent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    private void ustawWidoki(ConstraintLayout thisLayout) {
        float kolorTla = settings.getFloat("kolorTla", -1f);
        int tekstNaglowka = settings.getInt("tekstNaglowka", -1);
        String kolorPrzyciskow = settings.getString("kolorPrzyciskow", "brak");
        int tekstPowitania = settings.getInt("tekstPowitania", -1);

        if(tekstPowitania != -1) {
            TextView motd = (TextView) findViewById(R.id.motdText);
            motd.setText(tekstyPowitania[tekstPowitania]);
        }

        if(kolorTla != -1f) {
            float[] hsvColor = {kolorTla, 1, 1};
            thisLayout.setBackgroundColor(Color.HSVToColor(hsvColor));
        }

        if(tekstNaglowka != -1) {
            this.setTitle(tekstyNaglowka[tekstNaglowka]);
        }

        switch(kolorPrzyciskow) {
            case "Szary":
                Button przycisk1 = (Button) findViewById(R.id.ustawieniaButton);
                przycisk1.setBackgroundColor(Color.GRAY);
                Button przycisk2 = (Button) findViewById(R.id.wprowadzanieButton);
                przycisk2.setBackgroundColor(Color.GRAY);
                break;
            case "Zielony":
                przycisk1 = (Button) findViewById(R.id.ustawieniaButton);
                przycisk1.setBackgroundColor(Color.GREEN);
                przycisk2 = (Button) findViewById(R.id.wprowadzanieButton);
                przycisk2.setBackgroundColor(Color.GREEN);
                break;
            case "Czerwony":
                przycisk1 = (Button) findViewById(R.id.ustawieniaButton);
                przycisk1.setBackgroundColor(Color.RED);
                przycisk2 = (Button) findViewById(R.id.wprowadzanieButton);
                przycisk2.setBackgroundColor(Color.RED);
                break;
            default:
        }
    }
}
