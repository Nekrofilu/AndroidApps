package com.example.filu.simplediceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.kostkaButton)
    Button kostka;

    @OnClick(R.id.kostkaButton)
    public void onKostkaClick(View view) {
        Intent kostkaIntent = new Intent(this, DieSidesView.class);
        startActivity(kostkaIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
