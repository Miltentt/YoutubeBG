package com.example.youtubebg.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.youtubebg.Models.Video;
import com.example.youtubebg.R;
import com.example.youtubebg.YoutubeConfig;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Youtube_Player_Fragment extends Fragment {

    private static final String VIDEO_ID = "VIDEO_ID";

    private List<String> videoId;
    private YouTubePlayerSupportFragment youTubePlayerFragment;
    public Youtube_Player_Fragment() { }


    public static Youtube_Player_Fragment newInstance(List<String> videoId) {
        Youtube_Player_Fragment fragment = new Youtube_Player_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(VIDEO_ID, (Serializable) videoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            videoId = (List<String>) getArguments().getSerializable(VIDEO_ID);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View rootView = inflater.inflate(R.layout.fragment_youtube, container, false);
        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.flYoutubePlayer, youTubePlayerFragment).commit();
        youTubePlayerFragment.initialize(YoutubeConfig.getYtApi(), new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youTubePlayer.loadVideos(videoId,0,0);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {

            }
        });

        return rootView;
    }


      public void playVideo(int position)
      {
          youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
          FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
          transaction.replace(R.id.flYoutubePlayer, youTubePlayerFragment).commit();
        youTubePlayerFragment.initialize(YoutubeConfig.getYtApi(), new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youTubePlayer.loadVideos(videoId,position,0);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {

            }
        });


    }

}

