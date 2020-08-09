package com.example.youtubebg.Playlists.Views;

import android.annotation.SuppressLint;
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
    private List<String> ids = new ArrayList<>();
    private List<String> names = new ArrayList<>();


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.youtubeplayer);
        youtube_player_viewModel = ViewModelProviders.of(this).get(Youtube_Player_ViewModel.class);
        youtube_player_viewModel.createObservable((List<Video>) getIntent().getSerializableExtra("videos"));

        initRecycler();
        initObserver();
        youtube_player_viewModel.returVideos().subscribeWith(observer);
        initFragment();


    }

    @Override
    public void saveVideo(int position) {
        youtube_player_fragment.playVideo(position);
    }


    public void StartFloating() {
        Intent i = new Intent(YoutubePlayerr.this, Floating_Window_Service.class);
        i.putStringArrayListExtra("names", (ArrayList) names);
        i.putStringArrayListExtra("ids", (ArrayList) ids);
        startForegroundService(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.background_menu, menu);
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
        observer = new FlowableSubscriber<Video>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Video s) {
                names.add(s.getTitles());
                ids.add(s.getId());
                adapter.updateAdapter(names);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void initRecycler() {
        adapter = new Play_Playlist_Adapter(new ArrayList<>(), this);
        recyclerView = findViewById(R.id.videos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void initFragment() {
        youtube_player_fragment = Fragment_Player_Youtube.newInstance(ids);
        Fragment_Player_Youtube youtubeFragment = youtube_player_fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        }
    }

