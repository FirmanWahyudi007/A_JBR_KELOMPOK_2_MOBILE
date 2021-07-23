package com.example.temanautis.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temanautis.Model.VideoModel;
import com.example.temanautis.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.HolderData> {
    private Context ctx;

    public VideoAdapter(Context ctx, List<VideoModel> listVideo) {
        this.ctx = ctx;
        this.listVideo = listVideo;
    }

    private List<VideoModel> listVideo;
    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        VideoModel vm = listVideo.get(position);
        holder.id.setText(String.valueOf(vm.getId()));
        holder.judul.setText(vm.getJudul());
        holder.keterangan.setText(vm.getDeskripsi());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://wsjti.id/sipenyaut/public/video/"+vm.getId();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVideo.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView id,judul,keterangan;
        RelativeLayout card;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            judul = itemView.findViewById(R.id.judul_video);
            keterangan = itemView.findViewById(R.id.keterangan);
            card = itemView.findViewById(R.id.video_c);
        }
    }
}
