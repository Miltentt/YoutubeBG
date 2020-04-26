package com.example.youtubebg.Views;
import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.youtubebg.Fragments.Youtube_Player_Fragment;
import com.example.youtubebg.Models.Video;

import com.example.youtubebg.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;



import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class Floating_Window_Service extends IntentService {
   private Activity act;
   private  ArrayList<String> list = new ArrayList<>();
   private String first;
   private View myview;
   private int i=0;
   private WindowManager wm;
   private boolean minimized;
   private YouTubePlayerView youTubePlayerView;
   private ImageButton next;
   private int input=0;
   private ImageButton previous;
   private YouTubePlayerListener listener = new YouTubePlayerListener() {
       @Override
       public void onReady(YouTubePlayer youTubePlayer) {
           String videoId = list.get(i);
           youTubePlayer.loadVideo(videoId, 0f);
       }

       @Override
       public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {
if(playerState==PlayerConstants.PlayerState.ENDED)
{
    i++;
    youTubePlayer.loadVideo(list.get(i), 0f);
}
           if(playerState==PlayerConstants.PlayerState.PAUSED)
           {
               if(input==0)
               {

               }
               if(input==1)
               {
                   i++;
                   youTubePlayer.loadVideo(list.get(i), 0f);
               }

                   if(input==2)
                   {
                       i--;
                       youTubePlayer.loadVideo(list.get(i), 0f);
                   }
               input=0;
           }

       }

       @Override
       public void onPlaybackQualityChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackQuality playbackQuality) {

       }

       @Override
       public void onPlaybackRateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackRate playbackRate) {

       }

       @Override
       public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError playerError) {

       }

       @Override
       public void onCurrentSecond(YouTubePlayer youTubePlayer, float v) {

       }

       @Override
       public void onVideoDuration(YouTubePlayer youTubePlayer, float v) {

       }

       @Override
       public void onVideoLoadedFraction(YouTubePlayer youTubePlayer, float v) {

       }

       @Override
       public void onVideoId(YouTubePlayer youTubePlayer, String s) {

       }

       @Override
       public void onApiChange(YouTubePlayer youTubePlayer) {

       }
   };
    LinearLayout layout;


    public Floating_Window_Service() {
        super("xd");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        first= intent.getStringExtra("id");
        list.add(first);
for(int j=0;j< ((ArrayList<Video>) intent.getSerializableExtra("videos")).size();j++)
{
    list.add((((ArrayList<Video>) intent.getSerializableExtra("videos")).get(j).getId()));
}
    }

    @Override
    public void onCreate() {
        super.onCreate();


        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                //WindowManager.LayoutParams.TYPE_INPUT_METHOD |
                LAYOUT_FLAG,// | WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        params.gravity = Gravity.RIGHT | Gravity.TOP;
        myview = li.inflate(R.layout.youtubeplayer_service, null);
        ImageButton minimize = myview.findViewById(R.id.mini);
        layout = myview.findViewById(R.id.linearLayout2);
         youTubePlayerView = myview.findViewById(R.id.youtube_player_view);
         next = myview.findViewById(R.id.imageButton6);
         next.setOnClickListener(e->onNext());
         previous = myview.findViewById(R.id.imageButton7);
         previous.setOnClickListener(e->onPrevious());
        youTubePlayerView.setEnableAutomaticInitialization(false);
        youTubePlayerView.initialize(listener,false);
        minimize.setOnClickListener(e -> minimize());
        wm.addView(myview, params);


    }
    private void minimize() {
        if (minimized == false)
        {
            layout.getLayoutParams().height = 1;
        layout.getLayoutParams().width = 1;
        layout.requestLayout();
        minimized = true;
    } else
      {
          layout.getLayoutParams().height= WindowManager.LayoutParams.WRAP_CONTENT;
          layout.getLayoutParams().width= WindowManager.LayoutParams.MATCH_PARENT;
          layout.requestLayout();
          minimized=false;
      }
    }
public void onNext()
{
    input=1;
  youTubePlayerView.getYouTubePlayerWhenReady(YouTubePlayer::pause);
}
public void onPrevious()
{
    input=2;
    youTubePlayerView.getYouTubePlayerWhenReady(YouTubePlayer::pause);

}


}
