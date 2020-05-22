package com.example.youtubebg.Service;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.Service_ViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Floating_Window_Service extends Service {
   private View myview;
   private int i=0;
   private YouTubePlayerView youTubePlayerView;
   private int input=0;
    private NotificationManager notificationManager;
    private int position=0;
    private Observer<List<String>> observer;
    private List<String> names;
    private List<String> ids;
    private Observer<List<String>> observer2;
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
           String videoId = ids.get(i);
           youTubePlayer.loadVideo(videoId, 0f);
           onTrackPlay();
       }

       @Override
       public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {
if(playerState==PlayerConstants.PlayerState.ENDED)
{
    i++;
    youTubePlayer.loadVideo(ids.get(i), 0f);
}
           if(playerState==PlayerConstants.PlayerState.PAUSED)
           {
               if(input==0)
               {

               }
               if(input==1)
               {
                   i++;
                   youTubePlayer.loadVideo(ids.get(i), 0f);
               }

                   if(input==2)
                   {
                       i--;
                       youTubePlayer.loadVideo(ids.get(i), 0f);
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




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }





    @Override
    public void onCreate() {
        super.onCreate();
initObserver();
initObserver2();
        Service_ViewModel.getObservableNames().subscribe(observer);
Service_ViewModel.getObservableID().subscribe(observer2);
        creatNotification();
        this.registerReceiver(broadcastReceiver, new IntentFilter("track"));
        startService(new Intent(Floating_Window_Service.this, OnClearFromRecentService.class));
        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        myview = li.inflate(R.layout.youtubeplayer_service, null);
         youTubePlayerView = myview.findViewById(R.id.youtube_player_view);
        youTubePlayerView.setEnableAutomaticInitialization(false);
        youTubePlayerView.initialize(listener);



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
        Notification.createNotification(this, names.get(position),
                R.drawable.ic_pause_black_24dp, position, names.size()-1);
        input=2;
        youTubePlayerView.getYouTubePlayerWhenReady(YouTubePlayer::pause);

    }


    public void onTrackPlay() {

        Notification.createNotification(this, names.get(position),
                R.drawable.ic_pause_black_24dp, position, names.size()-1);
isPlaying=true;
        input=0;
youTubePlayerView.getYouTubePlayerWhenReady(YouTubePlayer::play);


    }


    public void onTrackPause() {

        Notification.createNotification(this, names.get(position),
                R.drawable.ic_play_arrow_black_24dp, position, names.size()-1);

isPlaying=false;
        input=0;
        youTubePlayerView.getYouTubePlayerWhenReady(YouTubePlayer::pause);

    }


    public void onTrackNext() {

        position++;
        Notification.createNotification(this, names.get(position),
                R.drawable.ic_pause_black_24dp, position, names.size()-1);
        input=1;
        youTubePlayerView.getYouTubePlayerWhenReady(YouTubePlayer::pause);

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
               Log.i("XXX",strings.get(0));
               names=strings;
            }


            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
    private void initObserver2()
    {
        observer2 = new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<String> strings) {
                ids=strings;
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
