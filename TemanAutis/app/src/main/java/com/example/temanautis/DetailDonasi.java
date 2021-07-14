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
import com.example.temanautis.Model.DonasiModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailDonasi extends AppCompatActivity {
    public DonasiModel dm;
    Button button;
    ImageView banner;
    TextView nama,tanggal,yayasan, keterangan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_donasi);
        button = findViewById(R.id.donasi2);
        banner = findViewById(R.id.detail_banner);
        nama = findViewById(R.id.detail_nama);
        keterangan = findViewById(R.id.detail_keterangan);
        yayasan = findViewById(R.id.detail_yayasan);
        tanggal = findViewById(R.id.dt_tanggal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailDonasi.this, Donasi.class);
                startActivity(intent);
            }
        });
        getDonasi();
    }

    private void getDonasi() {
        String data = getIntent().getStringExtra("extra");
        Gson gson = new Gson();
        dm = gson.fromJson(data, DonasiModel.class);
        nama.setText(dm.getNamaDonasi());
        yayasan.setText(dm.getYayasan());
        tanggal.setText(dm.getTanggal());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            keterangan.setText(Html.fromHtml(dm.getKeterangan(), Html.FROM_HTML_MODE_LEGACY));
        } else
            keterangan.setText(Html.fromHtml(dm.getKeterangan()));
        String url = "http://192.168.45.184:8000/images/"+dm.getBanner();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(banner);
    }
}