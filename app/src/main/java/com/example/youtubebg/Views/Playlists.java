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

import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.MainActivity_ViewModel;
import com.example.youtubebg.ViewModels.Playlists_ViewModel;
import com.example.youtubebg.adapters.Playlist_Adapter;
import com.example.youtubebg.adapters.Search_Adapter;

public class Playlists extends AppCompatActivity {
    RecyclerView recyclerView;
    private Playlists_ViewModel playlists_viewModel;
    private Playlist_Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists);
        playlists_viewModel = ViewModelProviders.of(this).get(Playlists_ViewModel.class);
initRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.search:
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
public void initRecycler()
{
    adapter = new Playlist_Adapter(playlists_viewModel.loadPlaylists());
    recyclerView = findViewById(R.id.playlists);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
}

}
