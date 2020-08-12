package com.example.youtubebg.Playlists.Views;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.youtubebg.Models.Video;
import com.example.youtubebg.Playlists.ViewModels.Youtube_Player_ViewModel;
import com.example.youtubebg.R;
import com.example.youtubebg.Service.Floating_Window_Service;
import com.example.youtubebg.Playlists.Adapters.Play_Playlist_Adapter;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.FlowableSubscriber;

public class YoutubePlayerr extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {

    private Play_Playlist_Adapter adapter;
    private RecyclerView recyclerView;
    private Youtube_Player_ViewModel youtube_player_viewModel;
    private Fragment_Player_Youtube youtube_player_fragment;
    private FlowableSubscriber<Video> observer;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.youtubeplayer);
        youtube_player_viewModel = ViewModelProviders.of(this).get(Youtube_Player_ViewModel.class);
        youtube_player_viewModel.createObservable((List<Video>) getIntent().getSerializableExtra("videos"));

        initRecycler();
        initObserver();

        initFragment();


    }

    @Override
    public void saveVideo(int position) {
        youtube_player_fragment.playVideo(position);
    }


    public void StartFloating() {
        Intent i = new Intent(YoutubePlayerr.this, Floating_Window_Service.class);
        i.putStringArrayListExtra("names", (ArrayList) youtube_player_viewModel.returnNames());
        i.putStringArrayListExtra("ids", (ArrayList) youtube_player_viewModel.returnIds());
        startForegroundService(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.background_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.background: {
                if (isMyServiceRunning(Floating_Window_Service.class) == false) {
                    StartFloating();
                } else {
                    Intent a = new Intent(YoutubePlayerr.this, Floating_Window_Service.class);
                    stopService(a);
                }
                break;
            }


        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void initObserver() {
       youtube_player_viewModel.returNamesLiveData().observe(this, new Observer<List<String>>() {
           @Override
           public void onChanged(List<String> strings) {
               adapter.updateAdapter(strings);
           }
       });
    }

    public void initRecycler() {
        adapter = new Play_Playlist_Adapter(new ArrayList<>(), this);
        recyclerView = findViewById(R.id.videos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void initFragment() {
        youtube_player_fragment = Fragment_Player_Youtube.newInstance(youtube_player_viewModel.returnIds());
        Fragment_Player_Youtube youtubeFragment = youtube_player_fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isMyServiceRunning(Floating_Window_Service.class) == true) {
            Intent a = new Intent(YoutubePlayerr.this, Floating_Window_Service.class);
            stopService(a);
        }
        }

}

