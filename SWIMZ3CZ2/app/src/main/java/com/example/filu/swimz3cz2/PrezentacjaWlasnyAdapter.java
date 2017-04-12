package com.example.filu.swimz3cz2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.filu.swimz3cz2.MainActivity.APP_PREFERENCES;
import static com.example.filu.swimz3cz2.Ustawienia.tekstyNaglowka;

public class PrezentacjaWlasnyAdapter extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SharedPreferences settings;
    String[] nazwiskaArray;
    String[] lubiaJablkaArray;
    ArrayList<String> nazwiska;
    ArrayList<String> pensje;
    ArrayList<String> ulubioneKolory;
    ArrayList<String> lubiaJablka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prezentacja_wlasny_adapter);
        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        ustawWidoki((ConstraintLayout) findViewById(R.id.dostosowanaLayout));

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

        nazwiskaArray = nazwiska.toArray(new String[0]);
        lubiaJablkaArray = lubiaJablka.toArray(new String[0]);

        MyAdapter adapter = new MyAdapter();
        ListView dostosowana = (ListView) findViewById(R.id.dostosowanaListView);
        dostosowana.setAdapter(adapter);
        dostosowana.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = "";
        text += "Nazwisko: " + nazwiska.get(position) + "\n\n";
        text += "Pensja: " + pensje.get(position) + "\n\n";
        text += "Ulubiony kolor: " + ulubioneKolory.get(position) + "\n\n";
        text += "Lubie jablka: " + lubiaJablka.get(position);

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
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

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return nazwiskaArray.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View mV;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(convertView == null) {
                switch(lubiaJablkaArray[position]) {
                    case "Tak":
                        convertView = inflater.inflate(R.layout.list_row_lubi, null);
                        break;
                    case "Nie":
                        convertView = inflater.inflate(R.layout.list_row_nie_lubi, null);
                        break;
                    default:
                        convertView = inflater.inflate(R.layout.list_row_nie_wie, null);
                }
            }
            mV = convertView;

            TextView tv1 = (TextView) mV.findViewById(R.id.row_tv1);
            tv1.setText(nazwiskaArray[position]);

            TextView tv2 = (TextView) mV.findViewById(R.id.row_tv2);
            tv2.setText("Lubię jablka: " + lubiaJablkaArray[position]);

            return mV;
        }
    }
}
