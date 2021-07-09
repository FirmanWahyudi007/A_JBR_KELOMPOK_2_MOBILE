package com.example.temanautis.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temanautis.Model.DataModel;
import com.example.temanautis.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listArtikel;

    public AdapterData(Context ctx, List<DataModel> listArtikel){
        this.ctx = ctx;
        this.listArtikel = listArtikel;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_artikel, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listArtikel.get(position);
        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvJudul.setText(dm.getJudul_artikel());
        holder.tvIsi.setText(dm.getIsi_artikel());


    }

    @Override
    public int getItemCount() {
        return listArtikel.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvJudul, tvIsi, tvId;
        ImageView Sampul;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvIsi = itemView.findViewById(R.id.tv_isi);
            Sampul = itemView.findViewById(R.id.sampul);
        }
    }
}
