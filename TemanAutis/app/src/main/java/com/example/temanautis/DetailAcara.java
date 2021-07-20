package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanautis.Model.AcaraModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailAcara extends AppCompatActivity {
    public AcaraModel am;
    TextView judul,tempat,isi,tanggal;
    ImageView gambar;
    Button kembali,link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acara);
        gambar = findViewById(R.id.sampulacara);
        judul = findViewById(R.id.judul_acara);
        tempat = findViewById(R.id.tempat_acara);
        isi = findViewById(R.id.isi_acara);
        tanggal = findViewById(R.id.tanggal_acara);
        kembali = findViewById(R.id.kembali);
        link = findViewById(R.id.link);
        getAcara();

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailAcara.this, Acara.class);
                startActivity(intent);
            }
        });
    }

    private void getAcara() {
        String data = getIntent().getStringExtra("Data");
        Gson gson = new Gson();
        am = gson.fromJson(data, AcaraModel.class);
        judul.setText(am.getNama_acara());
        tempat.setText(am.getTempat());
        isi.setText(am.getDeskripsi_acara());
        tanggal.setText(am.getJam_acara()+", "+am.getTanggal_acara());
        String url = "http://192.168.43.142:8000/images/thumbnail/"+am.getThumbnail();
        Log.d("mesagae",am.getLink_acara());
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(gambar);

            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String url = am.getLink_acara();
                    Toast.makeText(DetailAcara.this, "Tidak Ada Link", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }
}