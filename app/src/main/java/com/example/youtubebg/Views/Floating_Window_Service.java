package com.example.youtubebg.Views;
import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.Models.Video;

import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.Service_ViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class Floating_Window_Service extends IntentService  {
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
    private NotificationManager notificationManager;
    private int position=0;
    private Observer<List<String>> observer;
    private   boolean isPlaying = false;
    public final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getExtras().getString("actionname");
                Log.i("xd",action);
                switch (action){
                    case Notification.ACTION_PREVIUOS:
                        onTrackPrevious();
                        break;
                    case Notification.ACTION_PLAY:
                        if (isPlaying){

                            onTrackPause();
                        } else {
                            onTrackPlay();

                        }
                        break;
                    case Notification.ACTION_NEXT:

                        onTrackNext();
                        break;
                }
            }

    };
   private YouTubePlayerListener listener = new YouTubePlayerListener() {
       @Override
       public void onReady(YouTubePlayer youTubePlayer) {
           String videoId = list.get(i);
           youTubePlayer.loadVideo(videoId, 0f);
           onTrackPlay();
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
        creatNotification();
        this.registerReceiver(broadcastReceiver, new IntentFilter("track"));
        startService(new Intent(Floating_Window_Service.this, OnClearFromRecentService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
initObserver();
        Service_ViewModel.getObservable().subscribe(observer);


        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        myview = li.inflate(R.layout.youtubeplayer_service, null);
         youTubePlayerView = myview.findViewById(R.id.youtube_player_view);
        youTubePlayerView.setEnableAutomaticInitialization(false);
        youTubePlayerView.initialize(listener,false);



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

        position--;
        Notification.createNotification(this, list.get(position),
                R.drawable.ic_pause_black_24dp, position, list.size()-1);
onPrevious();

    }


    public void onTrackPlay() {

        Notification.createNotification(this, list.get(position),
                R.drawable.ic_pause_black_24dp, position, list.size()-1);


        isPlaying = true;

    }


    public void onTrackPause() {

        Notification.createNotification(this, list.get(position),
                R.drawable.ic_play_arrow_black_24dp, position, list.size()-1);


        isPlaying = false;

    }


    public void onTrackNext() {

        position++;
        Notification.createNotification(this, list.get(position),
                R.drawable.ic_pause_black_24dp, position, list.size()-1);
onNext();

    }

@Override
    public void onDestroy() {
    super.onDestroy();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        notificationManager.cancelAll();
    }
unregisterReceiver(broadcastReceiver);

    }

    private void initObserver()
    {
        observer = new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<String> strings) {
               Log.i("XXX",strings.get(1));
            }


            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }



}
