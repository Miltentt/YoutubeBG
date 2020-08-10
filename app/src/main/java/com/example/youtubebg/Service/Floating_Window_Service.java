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
import com.example.youtubebg.R;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import java.util.List;

import androidx.annotation.Nullable;


public class Floating_Window_Service extends Service {
    private YouTubePlayerView youTubePlayerView;
    private NotificationManager notificationManager;
    private int position = 0;
    private List<String> names;
    private List<String> ids;
    private boolean isPlaying = false;
    public final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getExtras().getString("actionname");

            switch (action) {
                case Notification.ACTION_PREVIUOS:
                    onTrackPrevious();
                    break;
                case Notification.ACTION_PLAY:
                    if (isPlaying) {

                        onTrackPause();
                    } else {
                        onTrackPlay();

                    }
                    break;
                case Notification.ACTION_NEXT: {
                    onTrackNext();
                    break;
                }

            }
        }
    };


        private YouTubePlayerListener listener = new YouTubePlayerListener() {

            @Override
            public void onVideoLoadedFraction(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoId(YouTubePlayer youTubePlayer, String s) {

            }

            @Override
            public void onVideoDuration(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onPlaybackRateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackRate playbackRate) {

            }

            @Override
            public void onPlaybackQualityChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackQuality playbackQuality) {

            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError playerError) {

            }

            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onApiChange(YouTubePlayer youTubePlayer) {

            }

            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = ids.get(position);
                youTubePlayer.loadVideo(videoId, 0f);
                onTrackPlay();
            }

            @Override
            public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {
                if (playerState == PlayerConstants.PlayerState.ENDED) {
                    position++;
                    youTubePlayer.loadVideo(ids.get(position), 0f);
                    startForeground(1, Notification.createNotification(Floating_Window_Service.this, names.get(position),
                            R.drawable.ic_pause_black_24dp, position, names.size() - 1));
                }
            }
        };


    @Override
    public void onCreate() {
        super.onCreate();

        creatNotification();
        this.registerReceiver(broadcastReceiver, new IntentFilter("track"));
         youTubePlayerView = new YouTubePlayerView(this);
        youTubePlayerView.setEnableAutomaticInitialization(false);
        youTubePlayerView.initialize(listener,false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        names = intent.getStringArrayListExtra("names");
        ids = intent.getStringArrayListExtra("ids");
        return super.onStartCommand(intent, flags, startId);
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
        startForeground(1,  Notification.createNotification(this, names.get(position),
                R.drawable.ic_pause_black_24dp, position, names.size()-1));

        youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> youTubePlayer.loadVideo(ids.get(position),0f));

    }


    public void onTrackPlay() {

        startForeground(1, Notification.createNotification(this, names.get(position),
                R.drawable.ic_pause_black_24dp, position, names.size()-1));
isPlaying=true;
youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> youTubePlayer.play());


    }


    public void onTrackPause() {

        startForeground(1, Notification.createNotification(this, names.get(position),
                R.drawable.ic_play_arrow_black_24dp, position, names.size()-1));

isPlaying=false;
        youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> youTubePlayer.pause());

    }


    public void onTrackNext() {

        position++;
        startForeground(1,Notification.createNotification(this, names.get(position),
                R.drawable.ic_pause_black_24dp, position, names.size()-1));
        youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> youTubePlayer.loadVideo(ids.get(position),0f));

    }

@Override
    public void onDestroy() {
    super.onDestroy();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        notificationManager.cancelAll();
    }
    youTubePlayerView.release();
unregisterReceiver(broadcastReceiver);
youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> youTubePlayer.pause());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
