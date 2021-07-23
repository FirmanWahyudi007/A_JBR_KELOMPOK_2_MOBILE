package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Adapter.AcaraAdapter;
import com.example.temanautis.Model.AcaraModel;
import com.example.temanautis.Model.AcaraResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Acara extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<AcaraModel> acaraModelList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acara);
        rvData = findViewById(R.id.lis_acara);
        recyclerView = findViewById(R.id.lis_acara);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        Button kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Acara.this, TabActivity.class);
                startActivity(intent);
            }
        });
        getAcara();
    }

    private void getAcara() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<AcaraResponse> tampilData = ardData.ardAcaraData();
        tampilData.enqueue(new Callback<AcaraResponse>() {
            @Override
            public void onResponse(Call<AcaraResponse> call, Response<AcaraResponse> response) {
                int kode = response.body().getCode();
                String pesan = response.body().getMessage();

                Toast.makeText(Acara.this, "code : "+kode+" | message : "+pesan, Toast.LENGTH_SHORT).show();

                acaraModelList = response.body().getData();

                adData = new AcaraAdapter(Acara.this, acaraModelList);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AcaraResponse> call, Throwable t) {

            }
        });
    }
}