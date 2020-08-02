package com.example.youtubebg.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.Views.MainActivity;
import com.example.youtubebg.ViewModels.Playlists_ViewModel;
import com.example.youtubebg.adapters.Playlist_Adapter;

import java.util.LinkedList;
import java.util.List;

public class Playlists extends AppCompatActivity implements Playlist_Adapter.callBack {
    RecyclerView recyclerView;
    private Playlists_ViewModel playlists_viewModel;
    private Playlist_Adapter adapter;
    private SingleObserver observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists);
        playlists_viewModel = ViewModelProviders.of(this).get(Playlists_ViewModel.class);
        initObserver();
        playlists_viewModel.loadPlaylists().subscribe(observer);
        adapter = new Playlist_Adapter(new LinkedList<Playlist_card>(),Playlists.this);
        initRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.layout.playlist_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        playlists_viewModel.loadPlaylists().subscribe(observer);

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
            case R.id.delete: {
                Intent i = new Intent(this, Delete_Playlist.class);
                startActivityForResult(i,1);
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
    public void openPlaylist(int id) {
        Intent i = new Intent(this, Play_Playlist.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    private void initObserver() {
        observer = new SingleObserver<List<Playlist_card>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Playlist_card> playlist_cards) {

                    adapter.update(playlist_cards);

            }

            @Override
            public void onError(Throwable e) {

            }
        };
    }
}