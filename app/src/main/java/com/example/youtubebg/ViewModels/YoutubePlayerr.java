package com.example.youtubebg.ViewModels;

import android.content.Intent;
import android.os.Bundle;

import com.example.youtubebg.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerr extends YouTubeBaseActivity {
    private YouTubePlayerView player;
    private YouTubePlayer.OnInitializedListener listener;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.youtubeplayer);
        player = findViewById(R.id.player);
String s = new String();
        Intent intent = getIntent();
Bundle bundl = intent.getExtras();
s = bundl.getString("id");

initialize(s);
    }


public void initialize(String id) {
    listener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            youTubePlayer.loadVideo(id);
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        }
    };
    player.initialize("AIzaSyDtg9GVjWLW_KzJzyNPsMKTYOYD8YDrod8",listener);
}
}
