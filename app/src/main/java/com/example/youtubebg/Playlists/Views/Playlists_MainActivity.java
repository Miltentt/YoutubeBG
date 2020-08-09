package com.example.youtubebg.Playlists.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.Views.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Playlists_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_playlist);
initiateFragment();
    }



    public void initiateFragment()
    {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.playlist_view,new Fragment_Playlists())
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
               if(getSupportFragmentManager().findFragmentByTag("delete")==null)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.playlist_view,new Fragment_Delete_Playlist(),"delete")
                        .addToBackStack(null)
                        .commit();
            }

        }
        return super.onOptionsItemSelected(item);
    }

}
