package com.example.youtubebg.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.MainActivity_ViewModel;
import com.example.youtubebg.ViewModels.Play_Playlist_ViewModel;
import com.example.youtubebg.adapters.Play_Playlist_Adapter;
import com.example.youtubebg.adapters.Playlist_Adapter;
import com.example.youtubebg.adapters.Popup_adapter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class Play_Playlist extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {
    private Play_Playlist_Adapter adapter;
    private RecyclerView recyclerView;
    private Playlist_card card;
    private SingleObserver observer;
    private int id;
    Play_Playlist_ViewModel play_playlist_viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_playlist);
        initObserver();
        id = getIntent().getIntExtra("id",0);
        play_playlist_viewModel = ViewModelProviders.of(this).get(Play_Playlist_ViewModel.class);
        play_playlist_viewModel.getPlaylist(id).subscribe(observer);
       initRecycler();
    }

    @Override
    public void saveVideo(String name) {
        Intent i = new Intent(this, YoutubePlayerr.class);
        play_playlist_viewModel.populate(card);
        i.putExtra("id", play_playlist_viewModel.findId(name));
        i.putExtra("first", play_playlist_viewModel.findName(name));
        play_playlist_viewModel.switchfirst(name);
        i.putExtra("videos", (Serializable) play_playlist_viewModel.shuffle(true));
        startActivity(i);
    }
public void initRecycler()
{
    adapter = new Play_Playlist_Adapter(new LinkedList<String>(), this);
    recyclerView = findViewById(R.id.recycler);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.layout.playlist_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        play_playlist_viewModel.getPlaylist(id).subscribe(observer);
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
                Intent i = new Intent(this, Delete_Songs.class);
                i.putExtra("playlist", (Serializable) card);
                startActivityForResult(i, 1);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
    private void initObserver() {
        observer = new SingleObserver<Playlist_card>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Playlist_card playlist_card) {

card=playlist_card;
adapter.updateAdapter(card.getNames());
            }

            @Override
            public void onError(Throwable e) {

            }
        };
    }


}