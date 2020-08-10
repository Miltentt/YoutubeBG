package com.example.youtubebg.Playlists.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.youtubebg.Playlists.ViewModels.Play_Playlist_SharedViewModel;
import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.Views.MainActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

public class Play_Playlist_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
private Play_Playlist_SharedViewModel play_playlist_sharedViewModel;
private  Fragment_Delete_Songs fragment_delete_songs = new Fragment_Delete_Songs();
private Fragment_Play_Playlist fragment_play_playlist = new Fragment_Play_Playlist();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        initDrawer();
play_playlist_sharedViewModel = ViewModelProviders.of(this).get(Play_Playlist_SharedViewModel.class);
        play_playlist_sharedViewModel.createLiveData(getIntent().getIntExtra("id",0));
       initFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.playlist_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.delete: {
               if(getSupportFragmentManager().findFragmentByTag("delete")==null)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.playlist_view,fragment_delete_songs,"delete")
                        .addToBackStack(null)
                        .commit();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void initDrawer()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search: {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }
            case R.id.playlist: {
                Intent i = new Intent(this, Playlists_MainActivity.class);
                startActivity(i);
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void initFragment()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.playlist_view,fragment_play_playlist)
                .commit();
    }
}
