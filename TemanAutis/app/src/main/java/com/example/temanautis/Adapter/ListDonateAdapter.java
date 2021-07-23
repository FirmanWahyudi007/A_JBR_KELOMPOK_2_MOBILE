package com.example.temanautis.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temanautis.Konfirmasi;
import com.example.temanautis.Model.ListDonateModel;
import com.example.temanautis.R;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static com.example.temanautis.R.id.card_listdonate;

public class ListDonateAdapter extends RecyclerView.Adapter<ListDonateAdapter.HolderData> {
    private Context ctx;
    private List<ListDonateModel> list;

    public ListDonateAdapter(Context ctx, List<ListDonateModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_donate, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ListDonateModel LD = list.get(position);
        holder.ld_id.setText(String.valueOf(LD.getId()));
        holder.nama_donasi.setText(LD.getNama_donasi());
        holder.tanggal.setText(LD.getTanggal());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.nominal.setText(formatRupiah.format(LD.getNominal()));
        if (LD.getKonfirmasi() == 0){
            holder.konfirmasi.setText("Belum Upload Bukti");
            holder.listDonate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, Konfirmasi.class);
                    Gson gson = new Gson();
                    String str = gson.toJson(list.get(position), ListDonateModel.class);
                    intent.putExtra("extra",str);
                    ctx.startActivity(intent);
                }
            });
        }else if(LD.getKonfirmasi() == 2){
            holder.konfirmasi.setText("Belum Konfirmasi");
        }else{
            holder.konfirmasi.setText("Konfirmasi");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView ld_id,nama_donasi,tanggal,konfirmasi,nominal;
        RelativeLayout listDonate;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            ld_id = itemView.findViewById(R.id.ld_id);
            nominal = itemView.findViewById(R.id.ld_nominal);
            nama_donasi = itemView.findViewById(R.id.ld_nama);
            tanggal = itemView.findViewById(R.id.ld_tanggal);
            listDonate = itemView.findViewById(card_listdonate);
            konfirmasi = itemView.findViewById(R.id.ld_konfirmasi);
        }
    }
}
