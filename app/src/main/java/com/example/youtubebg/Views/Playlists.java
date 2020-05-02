package com.example.youtubebg.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.MainActivity_ViewModel;
import com.example.youtubebg.ViewModels.Playlists_ViewModel;
import com.example.youtubebg.adapters.Playlist_Adapter;
import com.example.youtubebg.adapters.Search_Adapter;

import java.io.Serializable;
import java.util.List;

public class Playlists extends AppCompatActivity implements Playlist_Adapter.callBack {
    RecyclerView recyclerView;
    private Playlists_ViewModel playlists_viewModel;
    private Playlist_Adapter adapter;
    private Observer observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists);
        playlists_viewModel = ViewModelProviders.of(this).get(Playlists_ViewModel.class);
        initObserver();
        playlists_viewModel.loadPlaylists().subscribe(observer);
        initRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search: {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void initRecycler() {

        recyclerView = findViewById(R.id.playlists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void openPlaylist(Playlist_card card) {
        Intent i = new Intent(this, Play_Playlist.class);
        i.putExtra("playlist", card);
        startActivity(i);
    }

    private void initObserver() {
        observer = new Observer<List<Playlist_card>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Playlist_card> playlist_cards) {
                if (adapter == null) {
                    adapter = new Playlist_Adapter(playlist_cards, Playlists.this);
                } else {
                    adapter.update(playlist_cards);
                }
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}