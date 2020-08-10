package com.example.youtubebg.Search_Video.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.Views.Play_Youtube_Video;
import com.example.youtubebg.Search_Video.Views.Playlist_popup;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.MyViewHolder>  {

    private List<Search_Response.Item> results;
    private Context context;
    private FragmentManager fragmentManager;

    public Search_Adapter(List<Search_Response.Item> results, FragmentManager fragmentManager, Context context)
    {
        this.results = results;
        this.fragmentManager =fragmentManager;
        this.context= context;
    }




    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_search,parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(results.get(position).getSnippet().getTitle());
        Picasso.get().load(results.get(position).getSnippet().getThumbnails().getDefault().getUrl())
                .resize(480,271)
                .centerCrop()
                .into(holder.thumbnail);
        holder.add.setOnClickListener(e->{ openDialog(results.get(position)); });
        holder.thumbnail.setOnClickListener(e-> PlayVideo(position));
    }



    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public ImageButton add;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            add = itemView.findViewById(R.id.add);
        }
    }


    private void openDialog(Search_Response.Item video) {
        Playlist_popup popup = new Playlist_popup(video);
        popup.show(fragmentManager,"ezMPLE");

    }
    private void PlayVideo(int position) {
        Bundle bundl = new Bundle();
        bundl.putString("id",results.get(position).getId().getVideoId());
        Intent intent = new Intent(context, Play_Youtube_Video.class);
        intent.putExtras(bundl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void updateList(List<Search_Response.Item> response)
    {
        this.results = response;
        notifyDataSetChanged();
    }
}