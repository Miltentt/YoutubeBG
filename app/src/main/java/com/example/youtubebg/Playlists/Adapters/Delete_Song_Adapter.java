package com.example.youtubebg.Playlists.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.youtubebg.R;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Delete_Song_Adapter extends RecyclerView.Adapter<Delete_Song_Adapter.MyViewHolder> {

    private List<String> list= new LinkedList<>();
    private adapterCallBack callBack;
    public Delete_Song_Adapter(List<String> list, adapterCallBack callBack )
    {
        this.callBack=callBack;
        this.list=list;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_song_recycler,parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position));
        holder.delete.setOnClickListener(e->onClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageButton delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.grav);
            delete=itemView.findViewById(R.id.delete);
        }
    }

    public interface adapterCallBack
    {
        public void removeVideo(int position);

    }
    private void onClick(int position)
    {
        callBack.removeVideo(position);
    }
    public void updateAdapter(List<String> updatedlist)
    {
        this.list=updatedlist;
        notifyDataSetChanged();
    }
}

