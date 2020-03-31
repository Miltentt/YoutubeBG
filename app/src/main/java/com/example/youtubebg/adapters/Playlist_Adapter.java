package com.example.youtubebg.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Playlist_card;

import java.util.List;

public class Playlist_Adapter extends RecyclerView.Adapter<Playlist_Adapter.MyViewHolder> {

private List<Playlist_card> card;




    @NonNull
    @Override
    public Playlist_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Playlist_Adapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return card.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView video;
            TextView amount;
            TextView name;

        }
    }
}
