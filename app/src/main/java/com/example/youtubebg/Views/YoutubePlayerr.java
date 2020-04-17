package com.example.youtubebg.Views;

import android.content.Intent;
import android.os.Bundle;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class YoutubePlayerr extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {
    private YouTubePlayerView player;
    private YouTubePlayer.OnInitializedListener listener;
    private Play_Playlist_Adapter adapter;
    private RecyclerView recyclerView;
    private List<Video> videos;

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
 }

    @Override
    public void saveVideo(String name) {

        Youtube_Player_Fragment youtubeFragment = Youtube_Player_Fragment.newInstance(youtubePlaylist_viewModel.NextVideo((List<Video>) getIntent().getSerializableExtra("videos"),name));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();
    }
}
