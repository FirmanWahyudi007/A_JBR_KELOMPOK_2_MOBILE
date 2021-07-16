package com.example.temanautis.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temanautis.DetailArtikel;
import com.example.temanautis.DetailDonasi;
import com.example.temanautis.Donasi;
import com.example.temanautis.Model.DataModel;
import com.example.temanautis.Model.DonasiModel;
import com.example.temanautis.Model.YayasanModel;
import com.example.temanautis.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DonasiAdapter extends RecyclerView.Adapter<DonasiAdapter.HolderData>{
    private Context ctx;
    private List<DonasiModel> listDonasi;
    public DonasiAdapter(Context ctx, List<DonasiModel> listDonasi) {
        this.ctx = ctx;
        this.listDonasi = listDonasi;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_donasi, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DonasiModel ym = listDonasi.get(position);
        holder.yd_id.setText(String.valueOf(ym.getId()));
        holder.yd_nama.setText(ym.getNamaDonasi());
        holder.yd_yayasan.setText(ym.getNamaYayasan());
        holder.yd_keterangan.setText(ym.getKeterangan());
        String url = "http://192.168.1.6:8000/images/"+ym.getBanner();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.yd_banner);
        holder.List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailDonasi.class );
                Gson gson = new Gson();
                String str = gson.toJson(listDonasi.get(position), DonasiModel.class);
                intent.putExtra("extra", str);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDonasi.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView yd_nama, yd_yayasan, yd_keterangan,yd_id;
        ImageView yd_banner;
        CardView List;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            yd_id = itemView.findViewById(R.id.tv_idDonasi);
            yd_nama = itemView.findViewById(R.id.tv_penermiaDonasi);
            yd_yayasan = itemView.findViewById(R.id.tv_yayasanDonasi);
            yd_keterangan = itemView.findViewById(R.id.tv_keteranganDonasi);
            yd_banner = itemView.findViewById(R.id.yd_banner);
            List = itemView.findViewById(R.id.card_donasi);
        }
    }
}
