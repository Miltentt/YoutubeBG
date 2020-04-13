package com.example.youtubebg.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.example.youtubebg.Views.Play_Playlist;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Play_Playlist_Adapter extends RecyclerView.Adapter<Play_Playlist_Adapter.MyViewHolder> {

    private List<String> list= new LinkedList<>();
    private adapterCallBack callBack;
    public Play_Playlist_Adapter(List<String> list )
    {
        this.list=list;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_play_playlist,parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position));
        holder.name.setOnClickListener(e->onClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }

    public interface adapterCallBack
    {
        public void saveVideo(String name);

    }
    private void onClick(int position)
    {
callBack.saveVideo(list.get(position));
    }
}

