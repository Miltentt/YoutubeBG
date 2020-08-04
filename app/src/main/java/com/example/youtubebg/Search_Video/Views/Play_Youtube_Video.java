package com.example.youtubebg.Search_Video.Views;

import android.os.Bundle;

import com.example.youtubebg.R;
import com.example.youtubebg.YoutubeConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Play_Youtube_Video extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

private YouTubePlayerView youTubePlayerView;
private String id;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.acitivity_youtube_player);
youTubePlayerView = findViewById(R.id.youtubeplayer);
youTubePlayerView.initialize(YoutubeConfig.getYtApi(),this);
id = getIntent().getStringExtra("id");

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (!b) {
            youTubePlayer.loadVideo(id);
        }
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
