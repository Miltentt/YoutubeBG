package com.example.youtubebg.Views;
import android.app.Activity;
import android.app.Service;
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
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
public class Floating_Window_Service extends Service {
   private Activity act;
   private  ArrayList<String> list = new ArrayList<String>();
   private View myview;
   private WindowManager wm;
   private boolean minimized;
    LinearLayout layout;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
        YouTubePlayerView youTubePlayerView = myview.findViewById(R.id.youtube_player_view);

minimize.setOnClickListener(e->minimize());
        wm.addView(myview, params);


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "S0Q4gqBUs7c";
                youTubePlayer.loadVideo(videoId, 0f);
            }
        });
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


}
