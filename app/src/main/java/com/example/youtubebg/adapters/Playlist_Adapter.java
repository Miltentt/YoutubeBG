package com.example.youtubebg.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Playlist_Adapter extends RecyclerView.Adapter<Playlist_Adapter.MyViewHolder> {

private List<Playlist_card> card;
public callBack callBack;
public Playlist_Adapter(List<Playlist_card> card, callBack callBack)
{
    this.card=card;
    this.callBack=callBack;
}

public interface callBack{
   void openPlaylist(int id);
}


    @NonNull
    @Override
    public Playlist_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_playlist,parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Playlist_Adapter.MyViewHolder holder, int position) {
        Picasso.get().load(card.get(position).getPhoto()).into(holder.video);
        holder.name.setText(card.get(position).getName());
        holder.video.setOnClickListener(e->onClick(position));
    }

    @Override
    public int getItemCount() {
        return card.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView video;

        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           video = itemView.findViewById(R.id.video);
name = itemView.findViewById(R.id.grav);
        }
    }
    public void onClick(int position)
    {
        callBack.openPlaylist(card.get(position).getId());
    }
    public void update(List<Playlist_card> card)
    {
        this.card = card;
        notifyDataSetChanged();
    }
}
