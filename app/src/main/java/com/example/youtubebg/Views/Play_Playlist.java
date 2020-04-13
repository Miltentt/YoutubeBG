package com.example.youtubebg.Views;

import android.os.Bundle;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.example.youtubebg.adapters.Play_Playlist_Adapter;
import com.example.youtubebg.adapters.Playlist_Adapter;
import com.example.youtubebg.adapters.Popup_adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Play_Playlist extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {
private Play_Playlist_Adapter adapter;
private RecyclerView recyclerView;
private Playlist_card card;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_playlist);
        card = (Playlist_card) getIntent().getSerializableExtra("playlist");
        adapter = new Play_Playlist_Adapter(card.getNames());
        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void saveVideo(String name) {

    }
}
