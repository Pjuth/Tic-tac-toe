package com.example.pjuth.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Start(View v) {

        startActivity(new Intent(MainActivity.this, Game.class));
    }

    public void exit(View v) {
        System.exit(0);
    }
}
