package com.example.youtubebg.Views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationAction_Service extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.sendBroadcast(new Intent("Tracks")
                .putExtra("acitonname",intent.getAction()));
    }
}
