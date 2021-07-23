package com.example.temanautis.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.temanautis.Model.YayasanModel;
import com.example.temanautis.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YayasanAdapter extends RecyclerView.Adapter<YayasanAdapter.HolderData> {
    private Context ctx;
    private List<YayasanModel> listYayasan;
    public YayasanAdapter(Context ctx, List<YayasanModel> listYayasan) {
        this.ctx = ctx;
        this.listYayasan = listYayasan;
    }

    @NonNull
    @Override
    public YayasanAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_yayasan, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull YayasanAdapter.HolderData holder, int position) {
        YayasanModel ym = listYayasan.get(position);
        holder.ys_id.setText(String.valueOf(ym.getId()));
        holder.ys_nama.setText(ym.getNama_yayasan());
        holder.ys_alamat.setText(ym.getAlamat());
        holder.ys_nohp.setText(ym.getNo_telp());
        String url = "https://wsjti.id/sipenyaut/public/images/"+ym.getDokumentasi();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.ys_dokumentasi);
    }

    @Override
    public int getItemCount() {
        return listYayasan.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView ys_nama, ys_nohp, ys_alamat,ys_id;
        ImageView ys_dokumentasi;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            ys_id = itemView.findViewById(R.id.ys_id);
            ys_nama = itemView.findViewById(R.id.ys_nama);
            ys_nohp = itemView.findViewById(R.id.ys_nohp);
            ys_alamat = itemView.findViewById(R.id.ys_alamat);
            ys_dokumentasi = itemView.findViewById(R.id.ys_dokumentasi);
        }
    }
}
