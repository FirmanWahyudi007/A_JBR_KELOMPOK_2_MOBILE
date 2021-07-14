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
import com.example.temanautis.Adapter.AdapterData;
import com.example.temanautis.Model.DataModel;
import com.example.temanautis.Model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Artikel extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> lisData = new ArrayList<>();
    private TextView Hasil;
    private AdapterData adapterData;
    private SearchView searchView;
    String Judul_Artikel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        rvData = findViewById(R.id.rv_artikel);
        searchView = findViewById(R.id.search);
        recyclerView = findViewById(R.id.rv_artikel);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        artikelData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModel> call = apiRequestData.search(newText);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        lisData = response.body().getData();
                        adData = new AdapterData(Artikel.this, lisData);
                        recyclerView.setAdapter(adData);
                        adData.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });
                return false;
            }
        });

}


    public void artikelData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardArtikelData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(Artikel.this, "code : "+kode+" | message : "+pesan, Toast.LENGTH_SHORT).show();

                lisData = response.body().getData();

                adData = new AdapterData(Artikel.this, lisData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(Artikel.this, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}