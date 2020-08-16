package com.example.youtubebg.Service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.youtubebg.R;

import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationUtil {
    public static final String ACTION_PREVIUOS = "actionprevious";
    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_NEXT = "actionnext";
    public static final String DISMISS = "Dismiss";
    public static android.app.Notification createNotification(Context context, String name, int playbutton, int pos, int size)
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

Intent swipeIntent = new Intent(context,NotificationAction_Service.class).setAction(DISMISS);
PendingIntent onSwipeDismiss = PendingIntent.getBroadcast(context,0,swipeIntent,PendingIntent.FLAG_UPDATE_CURRENT);


android.app.Notification notification = new NotificationCompat.Builder(context, "channel1")
        .setSmallIcon(R.drawable.ic_music_note)
        .setOnlyAlertOnce(false)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setShowWhen(false)
.setContentTitle(name)
        .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0, 1, 2)
                .setMediaSession(mediaSessionCompat.getSessionToken()))
        .addAction(drw_previous, "Previous", pendingIntentPrevious)
            .addAction(playbutton, "Play", pendingIntentPlay)
            .addAction(drw_next, "Next", pendingIntentNext)
.setDeleteIntent(onSwipeDismiss)
        .build();
return notification;
    }


}
