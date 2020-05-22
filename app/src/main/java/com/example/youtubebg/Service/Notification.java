package com.example.youtubebg.Service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.youtubebg.R;
import com.example.youtubebg.Service.NotificationAction_Service;

import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification {
    public static final String ACTION_PREVIUOS = "actionprevious";
    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_NEXT = "actionnext";
    public static void createNotification(Context context, String name, int playbutton, int pos, int size)
    {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat( context, "tag");
        int drw_previous;
        PendingIntent pendingIntentPrevious;

        if (pos == 0){
            pendingIntentPrevious = null;
            drw_previous = 0;
        } else {
            Intent intentPrevious = new Intent(context, NotificationAction_Service.class)
                    .setAction(ACTION_PREVIUOS);
            pendingIntentPrevious = PendingIntent.getBroadcast(context, 0,
                    intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_previous = R.drawable.ic_skip_previous_black_24dp;
        }
        Intent intentPlay = new Intent(context, NotificationAction_Service.class)
                .setAction(ACTION_PLAY);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0,
                intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pendingIntentNext;
        int drw_next;
        if (pos == size){
            pendingIntentNext = null;
            drw_next = 0;
        } else {
            Intent intentNext = new Intent(context, NotificationAction_Service.class)
                    .setAction(ACTION_NEXT);
            pendingIntentNext = PendingIntent.getBroadcast(context, 0,
                    intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_next = R.drawable.ic_skip_next_black_24dp;

        }





android.app.Notification notification = new NotificationCompat.Builder(context, "channel1")
        .setSmallIcon(R.drawable.ic_music_note)
        .setOnlyAlertOnce(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setOnlyAlertOnce(true)//show notification for only first time
        .setShowWhen(false)
.setContentTitle(name)
        .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0, 1, 2)
                .setMediaSession(mediaSessionCompat.getSessionToken()))
        .addAction(drw_previous, "Previous", pendingIntentPrevious)
            .addAction(playbutton, "Play", pendingIntentPlay)
            .addAction(drw_next, "Next", pendingIntentNext)

        .build();
notificationManagerCompat.notify(1,notification);
    }


}
