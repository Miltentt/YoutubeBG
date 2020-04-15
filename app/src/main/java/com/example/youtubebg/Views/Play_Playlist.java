package com.example.youtubebg.Views;

import android.content.Intent;
import android.os.Bundle;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.MainActivity_ViewModel;
import com.example.youtubebg.ViewModels.Play_Playlist_ViewModel;
import com.example.youtubebg.adapters.Play_Playlist_Adapter;
import com.example.youtubebg.adapters.Playlist_Adapter;
import com.example.youtubebg.adapters.Popup_adapter;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Play_Playlist extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {
private Play_Playlist_Adapter adapter;
private RecyclerView recyclerView;
private Playlist_card card;
    Play_Playlist_ViewModel play_playlist_viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_playlist);
        card = (Playlist_card) getIntent().getSerializableExtra("playlist");
        play_playlist_viewModel = ViewModelProviders.of(this).get(Play_Playlist_ViewModel.class);
        adapter = new Play_Playlist_Adapter(card.getNames(),this);
        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void saveVideo(String name) {
Intent i = new Intent(this,YoutubePlayerr.class);
play_playlist_viewModel.populate(card);
i.putExtra("id",play_playlist_viewModel.findId(name));
play_playlist_viewModel.switchfirst(name);
i.putExtra("videos", (Serializable) play_playlist_viewModel.shuffle(true));
startActivity(i);
    }
}
