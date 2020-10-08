package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarRetrofit(View view){
        Intent intent = new Intent(MainActivity.this, RetrofitTela.class);
        startActivity(intent);
    }

    public void iniciarAsynctask(View view){
        Intent intent = new Intent(MainActivity.this, asynctask.class);
        startActivity(intent);
    }
}