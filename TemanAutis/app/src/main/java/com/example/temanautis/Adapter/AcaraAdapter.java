package com.example.temanautis.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temanautis.DetailAcara;
import com.example.temanautis.Model.AcaraModel;
import com.example.temanautis.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AcaraAdapter extends RecyclerView.Adapter<AcaraAdapter.HolderData> {

    private Context context;
    private List<AcaraModel> acaraModelList;

    public AcaraAdapter(Context context, List<AcaraModel> acaraModelList) {
        this.context = context;
        this.acaraModelList = acaraModelList;
    }


    @NonNull
    @Override
    public AcaraAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_acara, parent, false);
        AcaraAdapter.HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AcaraAdapter.HolderData holder, int position) {
        AcaraModel am = acaraModelList.get(position);
        holder.id.setText(String.valueOf(am.getId()));
        holder.nama.setText(am.getNama_acara());
        holder.tempat.setText(am.getTempat());
        holder.tanggal.setText(am.getTanggal_acara());
        String url = "http://192.168.1.6:8000/images/thumbnail/"+am.getThumbnail();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.gambar);
        holder.rl_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailAcara.class);
                Gson gson = new Gson();
                String string = gson.toJson(acaraModelList.get(position), AcaraModel.class);
                intent.putExtra("Data", string);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return acaraModelList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView id,nama,tanggal,tempat;
        RelativeLayout rl_ac;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            id = itemView.findViewById(R.id.id);
            nama = itemView.findViewById(R.id.judul);
            tanggal = itemView.findViewById(R.id.ac_tanggal);
            tempat = itemView.findViewById(R.id.ac_tempat);
            rl_ac = itemView.findViewById(R.id.ac_acara);
        }
    }
}
