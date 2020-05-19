package com.example.youtubebg.Views;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.youtubebg.Fragments.Youtube_Player_Fragment;
import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Video;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.Play_Playlist_ViewModel;
import com.example.youtubebg.ViewModels.Service_ViewModel;
import com.example.youtubebg.ViewModels.YoutubePlaylist_ViewModel;
import com.example.youtubebg.adapters.Play_Playlist_Adapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class YoutubePlayerr extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {

    private Play_Playlist_Adapter adapter;
    private RecyclerView recyclerView;
    private   boolean isPlaying = false;
    BroadcastReceiver broadcastReceiver;
    private NotificationManager notificationManager;
    private int position=0;
    private YoutubePlaylist_ViewModel youtubePlaylist_viewModel;




    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.youtubeplayer);


        youtubePlaylist_viewModel = ViewModelProviders.of(this).get(YoutubePlaylist_ViewModel.class);

        Youtube_Player_Fragment youtubeFragment = Youtube_Player_Fragment.newInstance(youtubePlaylist_viewModel.getId((List<Video>) getIntent().getSerializableExtra("videos"),getIntent().getStringExtra("id")));
      getSupportFragmentManager().beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();

        adapter = new Play_Playlist_Adapter(youtubePlaylist_viewModel.getNames((List<Video>) getIntent().getSerializableExtra("videos")), this);
        recyclerView = findViewById(R.id.videos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        canDrawOverlays();
 }

    @Override
    public void saveVideo(String name) {

        Youtube_Player_Fragment youtubeFragment = Youtube_Player_Fragment.newInstance(youtubePlaylist_viewModel.NextVideo((List<Video>) getIntent().getSerializableExtra("videos"),name));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();
    }


    public void StartFloating(){
        Intent i = new Intent(YoutubePlayerr.this,Floating_Window_Service.class);
        List<String> names = new LinkedList<>();
        names.add(getIntent().getStringExtra("first"));
        List<Video> list = (List<Video>) getIntent().getSerializableExtra("videos");
        for(int j = 0; j<list.size(); j++)
        {
            names.add(youtubePlaylist_viewModel.getNames((List<Video>) getIntent().getSerializableExtra("videos")).get(j));
        }
        Service_ViewModel.makeObservable(names);
        List<String> ids = new LinkedList<>();
       ids = youtubePlaylist_viewModel.getId((List<Video>)getIntent().getSerializableExtra("videos"),getIntent().getStringExtra("id"));
        Service_ViewModel.makeObservableI(ids);



        startService(i);
    }




    public void canDrawOverlays()
    {
        if(Settings.canDrawOverlays(getApplicationContext())&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){



        }
        else{
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION), 1);
        }
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
StartFloating();
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

}
