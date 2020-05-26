package com.example.youtubebg.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationAction_Service extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       Intent broadcastIntent = new Intent("track");
                broadcastIntent.putExtra("actionname",intent.getAction());
                context.sendBroadcast(broadcastIntent);
    }
}
