package com.example.youtubebg.Search_Video.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.Playlists.Views.Playlists_MainActivity;
import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.ViewModel.MainActivity_ViewModel;
import com.example.youtubebg.Search_Video.Adapters.Search_Adapter;
import com.google.android.material.navigation.NavigationView;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements Fragment_search.searchresult,NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private Search_Adapter adapter;
    private MainActivity_ViewModel mainActivity_viewModel;
    private SingleObserver<Search_Response> observer;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CompositeDisposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity_viewModel = ViewModelProviders.of(this).get(MainActivity_ViewModel.class);
        initRecycler();
        initDrawer();

    }

    @Override
    public void search(String search) {
        mainActivity_viewModel.getSearch(search);
      if(observer==null)
      {
          initObserver();
      }
        mainActivity_viewModel.getObservable().subscribe(observer);
    }




    private void initRecycler()
    {
        adapter = new Search_Adapter(new Search_Response().getItems(), this.getSupportFragmentManager(), this);
        recyclerView = findViewById(R.id.Youtube_Search_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }





    private void initObserver()
    {
        observer.onSuccess((Search_Response)t->adapter.updateList());
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
            case R.id.playlist: {
                Intent i = new Intent(this, Playlists_MainActivity.class);
                startActivity(i);
                break;
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}


