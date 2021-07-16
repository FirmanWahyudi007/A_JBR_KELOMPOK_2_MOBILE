package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Adapter.ListDonateAdapter;
import com.example.temanautis.Model.ListDonateModel;
import com.example.temanautis.Model.ListDonateResponse;
import com.example.temanautis.Model.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDonateActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager rvLm;
    private List<ListDonateModel> listData = new ArrayList<>();
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donate);
        rvData = findViewById(R.id.rv_listdonate);
        rvLm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rvLm);
        SessionManager sessionManager = new SessionManager(getBaseContext());
        token = (String) sessionManager.getUserDetail().get(SessionManager.TOKEN);
        listdonateData();
    }

    private void listdonateData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ListDonateResponse> tampilData = ardData.ardListDonateData("Bearer "+ token);
        tampilData.enqueue(new Callback<ListDonateResponse>() {
            @Override
            public void onResponse(Call<ListDonateResponse> call, Response<ListDonateResponse> response) {

                int kode = response.body().getCode();
                String pesan = response.body().getMessage();
                Toast.makeText(ListDonateActivity.this, "code : "+kode+" | message : "+pesan, Toast.LENGTH_SHORT).show();
                listData = response.body().getData();

                adData = new ListDonateAdapter(ListDonateActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListDonateResponse> call, Throwable t) {
                Toast.makeText(ListDonateActivity.this, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", "onResponse: " + token);
            }
        });
    }
}