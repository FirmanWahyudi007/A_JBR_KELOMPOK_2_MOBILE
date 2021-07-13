package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.temanautis.Model.DataModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.Inet4Address;

public class DetailArtikel extends AppCompatActivity {
    public DataModel dm;
    ImageView sampul;
    TextView judul,tanggal,isi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        sampul = findViewById(R.id.dt_sampul);
        judul = findViewById(R.id.dt_judul);
        isi = findViewById(R.id.dt_isi);
        tanggal = findViewById(R.id.dt_tanggal);
        getArtikel();
    }

    private void getArtikel() {
        String data = getIntent().getStringExtra("extra");
        Gson gson = new Gson();
        dm = gson.fromJson(data, DataModel.class);
        judul.setText(dm.getJudul_artikel());
        tanggal.setText(dm.getTanggal());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isi.setText(Html.fromHtml(dm.getIsi_artikel(), Html.FROM_HTML_MODE_LEGACY));
        } else
            isi.setText(Html.fromHtml(dm.getIsi_artikel()));
        String url = "http://192.168.45.184:8000/images/"+dm.getSampul();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(sampul);
    }
}