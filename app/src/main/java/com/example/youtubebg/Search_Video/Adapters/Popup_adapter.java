package com.example.youtubebg.Search_Video.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Popup_adapter extends RecyclerView.Adapter<Popup_adapter.MyViewHolder> {

    private List<Playlist_card> card= new LinkedList<>();
    private adapterCallBack callBack;
    public Popup_adapter(List<Playlist_card> card)
    {


        this.card=card;

    }
public void setCallBack(adapterCallBack callBack)
{
 this.callBack= callBack;

}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_popup_recycler,parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(card.get(position).getName());
        holder.name.setOnClickListener(e->onClick(position));
    }

    @Override
    public int getItemCount() {
        return card.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.grav);
        }
    }

    public interface adapterCallBack
    {
        public void saveVideo(Playlist_card card);

    }
    private void onClick(int position)
    {
callBack.saveVideo(card.get(position));
    }
    public void update(List<Playlist_card> card)
    {
        this.card = card;
        notifyDataSetChanged();
    }
}
