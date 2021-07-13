package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Adapter.YayasanAdapter;
import com.example.temanautis.Model.YayasanModel;
import com.example.temanautis.Model.YayasanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Yayasan extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager rvLm;
    private List<YayasanModel> listData = new ArrayList<>();
    private TextView Hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yayasan);
        rvData = findViewById(R.id.rv_yayasan);
        rvLm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rvLm);
        yayasanData();
    }


    private void yayasanData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<YayasanResponse> tampilData= ardData.ardYayasanData();

        tampilData.enqueue(new Callback<YayasanResponse>() {
            @Override
            public void onResponse(Call<YayasanResponse> call, Response<YayasanResponse> response) {
                int kode = response.body().getCode();
                String pesan = response.body().getMessage();
                Toast.makeText(Yayasan.this, "code : "+kode+" | message : "+pesan, Toast.LENGTH_SHORT).show();
                listData = response.body().getData();

                adData = new YayasanAdapter(Yayasan.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<YayasanResponse> call, Throwable t) {
                Toast.makeText(Yayasan.this, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
       
    }
}