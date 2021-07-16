package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Model.DonateResponse;
import com.example.temanautis.Model.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Donasi extends AppCompatActivity {
    TextView id;
    EditText nominal;
    EditText metode;
    EditText keterangan;
    APIRequestData apiInterface;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi);
        id = findViewById(R.id.editTextId);
        nominal = findViewById(R.id.editTextNominal);
        metode = findViewById(R.id.editTextMetode);
        keterangan = findViewById(R.id.editTextKeterangan);
        String data = getIntent().getStringExtra("extra");
        id.setText(data);
        SessionManager sessionManager = new SessionManager(getBaseContext());
        token = (String) sessionManager.getUserDetail().get(SessionManager.TOKEN);
    }

    public void onDonasiClick(View view) {
        apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
        Log.d("TAG", "onMasukClick: "+id.getText());
        Log.d("TAG", "onMasukClick: "+nominal.getText());
        Log.d("TAG", "onMasukClick: "+metode.getText().toString());
        Log.d("TAG", "onMasukClick: "+keterangan.getText().toString());
        Call<DonateResponse> DonateCall = apiInterface.Donate("Bearer "+ token, Integer.parseInt(id.getText().toString()), Integer.parseInt(nominal.getText().toString()),metode.getText().toString(),keterangan.getText().toString());
        DonateCall.enqueue(new Callback<DonateResponse>() {
            @Override
            public void onResponse(Call<DonateResponse> call, Response<DonateResponse> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(Donasi.this, Transfer.class));
                    Log.d("onResponse", "onResponse: " + response.message());
                    finish();
                }else {
                    Toast.makeText(Donasi.this, "Gagal", Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<DonateResponse> call, Throwable t) {
                Toast.makeText(Donasi.this, "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }
}