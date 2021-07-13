package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Adapter.DonasiAdapter;
import com.example.temanautis.Model.DonasiModel;
import com.example.temanautis.Model.DonasiResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarDonasi extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DonasiModel> listDonasi = new ArrayList<>();
    private TextView Hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_donasi);
        rvData = findViewById(R.id.rv_donasi);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        DonasiData();
    }

    private void DonasiData() {
        APIRequestData ardDonasi = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<DonasiResponse> tampilData = ardDonasi.ardDonasiData();
        tampilData.enqueue(new Callback<DonasiResponse>() {
            @Override
            public void onResponse(Call<DonasiResponse> call, Response<DonasiResponse> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();
                Toast.makeText(DaftarDonasi.this, "code : "+code+" | message : "+message, Toast.LENGTH_SHORT).show();
                listDonasi = response.body().getData();
                adData = new DonasiAdapter(DaftarDonasi.this, listDonasi);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DonasiResponse> call, Throwable t) {
                Toast.makeText(DaftarDonasi.this, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}