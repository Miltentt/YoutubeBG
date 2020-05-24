package com.example.youtubebg.Views;

import com.example.youtubebg.ViewModels.Delete_Playlist_ViewModel;
import com.example.youtubebg.adapters.Delete_Playlist_Adapter;
import com.example.youtubebg.adapters.Playlist_Adapter;

import android.app.ActionBar;
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
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.MainActivity_ViewModel;
import com.example.youtubebg.ViewModels.Playlists_ViewModel;
import com.example.youtubebg.adapters.Playlist_Adapter;
import com.example.youtubebg.adapters.Search_Adapter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import androidx.annotation.Nullable;

public class Delete_Playlist extends AppCompatActivity implements Delete_Playlist_Adapter.callBack {
    RecyclerView recyclerView;
    private Delete_Playlist_ViewModel playlists_viewModel;
    private Delete_Playlist_Adapter adapter;
    private SingleObserver observer;
    private List<Playlist_card> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists);
        playlists_viewModel = ViewModelProviders.of(this).get(Delete_Playlist_ViewModel.class);
        initObserver();
        playlists_viewModel.loadPlaylists().subscribe(observer);
        adapter = new Delete_Playlist_Adapter(new LinkedList<Playlist_card>(), this);
        initRecycler();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void initRecycler() {

        recyclerView = findViewById(R.id.playlists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                setResult(RESULT_OK);
                this.finish();
        break;
        }
        return true;
    }


    @Override
    public void openPlaylist(Playlist_card card) {
   playlists_viewModel.deletePlaylist(card);
list.remove(card);
adapter.updateList(list);
    }

    private void initObserver() {
        observer = new SingleObserver<List<Playlist_card>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Playlist_card> playlist_cards) {
list = playlist_cards;
                adapter.updateList(list);

            }

            @Override
            public void onError(Throwable e) {

            }
        };
    }
}


