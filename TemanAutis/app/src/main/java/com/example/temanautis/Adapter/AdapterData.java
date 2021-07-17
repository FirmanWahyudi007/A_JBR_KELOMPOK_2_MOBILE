package com.example.temanautis.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temanautis.DetailArtikel;
import com.example.temanautis.Model.DataModel;
import com.example.temanautis.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvIsi.setText(Html.fromHtml(dm.getIsi_artikel(), Html.FROM_HTML_MODE_LEGACY));
        } else
            holder.tvIsi.setText(Html.fromHtml(dm.getIsi_artikel()));
        String url = "http://192.168.43.142:8000/images/"+dm.getSampul();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.Sampul);
        holder.List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailArtikel.class );
                Gson gson = new Gson();
                String str = gson.toJson(listArtikel.get(position), DataModel.class);
                intent.putExtra("extra", str);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listArtikel.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvJudul, tvIsi, tvId;
        ImageView Sampul;
        RelativeLayout List;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvIsi = itemView.findViewById(R.id.tv_isi);
            Sampul = itemView.findViewById(R.id.sampul);
            List = itemView.findViewById(R.id.card_artikel);
        }
    }
}