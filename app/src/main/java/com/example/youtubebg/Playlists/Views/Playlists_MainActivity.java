package com.example.youtubebg.Playlists.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.Views.MainActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import io.reactivex.disposables.CompositeDisposable;

public class Playlists_MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_playlist);
initDrawer();
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
                        .replace(R.id.playlist_view,new Fragment_Delete_Playlist(),"delete")
                        .addToBackStack(null)
                        .commit();
            }
            case android.R.id.home: {
                drawerLayout.openDrawer(Gravity.LEFT);
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

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
