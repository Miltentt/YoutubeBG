package com.example.youtubebg.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Delete_Playlist_Adapter extends RecyclerView.Adapter<Delete_Playlist_Adapter.MyViewHolder>  {

    private List<Playlist_card> results;

    public callBack callBack;
    public interface callBack{
        void openPlaylist(Playlist_card card);
    }
    public Delete_Playlist_Adapter(List<Playlist_card> results, callBack callBack)
    {
        this.results = results;
        this.callBack= callBack;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_playlist_recycler,parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(results.get(position).getName());
        Picasso.get().load(results.get(position).getPhoto()).into(holder.thumbnail);
        holder.delete.setOnClickListener(e->{ onClick(position); });




    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public ImageButton delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.grav);
            thumbnail = itemView.findViewById(R.id.video);
            delete = itemView.findViewById(R.id.delete);




        }



    }

    public void updateList(List<Playlist_card> response)
    {
        this.results = response;
        notifyDataSetChanged();
    }
    public void onClick(int position)
    {
        callBack.openPlaylist(results.get(position));
    }
}

