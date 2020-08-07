package com.example.youtubebg.Playlists.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.youtubebg.Playlists.ViewModels.Play_Playlist_SharedViewModel;
import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.Views.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class Play_Playlist_Activity extends AppCompatActivity {

private Play_Playlist_SharedViewModel play_playlist_sharedViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
play_playlist_sharedViewModel = ViewModelProviders.of(this).get(Play_Playlist_SharedViewModel.class);
        play_playlist_sharedViewModel.createLiveData(getIntent().getIntExtra("id",0));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.playlist_view,new Fragment_Play_Playlist())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.layout.playlist_menu, menu);
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
            case R.id.delete: {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.playlist_view,new Fragment_Delete_Songs())
                        .addToBackStack(null)
                        .commit();


                break;
            }
            case R.id.playlist:
            {
                Intent i = new Intent(this, Playlists_MainActivity.class);
                startActivity(i);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }




}
