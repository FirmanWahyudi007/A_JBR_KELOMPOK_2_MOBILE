package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Adapter.AdapterData;
import com.example.temanautis.Adapter.YayasanAdapter;
import com.example.temanautis.Model.ResponseModel;
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
    private SearchView searchView;
    private AdapterData adapterData;
    String Judul_Artikel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yayasan);
        rvData = findViewById(R.id.rv_yayasan);
        searchView = findViewById(R.id.search);
        recyclerView = findViewById(R.id.rv_yayasan);
        rvLm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rvLm);
        Button kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Yayasan.this, TabActivity.class);
                startActivity(intent);
            }
        });
        yayasanData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<YayasanResponse> call = apiRequestData.searchYayasan(newText);
                call.enqueue(new Callback<YayasanResponse>() {
                    @Override
                    public void onResponse(Call<YayasanResponse> call, Response<YayasanResponse> response) {
                        listData = response.body().getData();
                        adData = new YayasanAdapter(Yayasan.this, listData);
                        recyclerView.setAdapter(adData);
                        adData.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<YayasanResponse> call, Throwable t) {

                    }
                });
                return false;
            }
        });
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