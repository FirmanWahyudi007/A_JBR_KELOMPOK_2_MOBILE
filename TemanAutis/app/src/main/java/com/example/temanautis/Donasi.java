package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Donasi extends AppCompatActivity {
    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi);
        id = findViewById(R.id.editTextId);
        String data = getIntent().getStringExtra("extra");
        id.setText(data);
    }
}