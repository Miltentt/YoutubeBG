package com.example.youtubebg.Views;

import androidx.annotation.Nullable;

import com.example.youtubebg.ViewModels.Delete_Songs_ViewModel;
import com.example.youtubebg.adapters.Delete_Song_Adapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;

public class Delete_Songs extends AppCompatActivity implements Delete_Song_Adapter.adapterCallBack {
    private Delete_Song_Adapter adapter;
    private RecyclerView recyclerView;
    private Playlist_card card;
    Delete_Songs_ViewModel play_playlist_viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_playlist);
        card = (Playlist_card) getIntent().getSerializableExtra("playlist");
        play_playlist_viewModel = ViewModelProviders.of(this).get(Delete_Songs_ViewModel.class);
       initRecycler();
    }

    @Override
    public void removeVideo(int position) {
        card.getNames().remove(position);
        card.getVideos().remove(position);
        adapter.updateAdapter(card.getNames());
        play_playlist_viewModel.deletePlaylist(card);
        play_playlist_viewModel.updatePlaylist(card);
    }

    public void initRecycler() {
        adapter = new Delete_Song_Adapter(card.getNames(), this);
        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}