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
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.youtubebg.Fragments.Youtube_Player_Fragment;
import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Video;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.Play_Playlist_ViewModel;
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

private static Action actioninterface;
    public interface Action
    {
        public void Whichaction(String action);

    }
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


    public void StartFloating(View v){
        Intent i = new Intent(YoutubePlayerr.this,Floating_Window_Service.class);
        i.putExtra("id",getIntent().getStringExtra("id"));
        i.putExtra("videos",getIntent().getSerializableExtra("videos"));

        startService(i);
    }


    public void canDrawOverlays()
    {
        if(Settings.canDrawOverlays(this)==false)
        {
            Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:" + getPackageName()));

            startActivity(i);

        }
    }

    private void creatNotification()
    {
        NotificationChannel channel = new NotificationChannel("channel1","name", NotificationManager.IMPORTANCE_HIGH);
        notificationManager = getSystemService(NotificationManager.class);
        if(notificationManager != null)
        {
            notificationManager.createNotificationChannel(channel);
        }

    }
    public void onTrackPrevious() {



    }


    public void onTrackPlay() {



    }


    public void onTrackPause() {



    }


    public void onTrackNext() {



    }
public static Action getActioninterface()
{
    return actioninterface;
}


}
