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

import com.example.youtubebg.Fragments.Youtube_Player_Fragment;
import com.example.youtubebg.Models.Video;
import com.example.youtubebg.R;

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

public class Floating_Window_Service extends Service {
   private Activity act;
   private  ArrayList<String> list = new ArrayList<String>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FragmentManager popup = new FragmentManager() {
            @NonNull
            @Override
            public FragmentTransaction beginTransaction() {
                return null;
            }

            @Override
            public boolean executePendingTransactions() {
                return false;
            }

            @Nullable
            @Override
            public Fragment findFragmentById(int id) {
                return null;
            }

            @Nullable
            @Override
            public Fragment findFragmentByTag(@Nullable String tag) {
                return null;
            }

            @Override
            public void popBackStack() {

            }

            @Override
            public boolean popBackStackImmediate() {
                return false;
            }

            @Override
            public void popBackStack(@Nullable String name, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(@Nullable String name, int flags) {
                return false;
            }

            @Override
            public void popBackStack(int id, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(int id, int flags) {
                return false;
            }

            @Override
            public int getBackStackEntryCount() {
                return 0;
            }

            @NonNull
            @Override
            public BackStackEntry getBackStackEntryAt(int index) {
                return null;
            }

            @Override
            public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

            }

            @Override
            public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

            }

            @Override
            public void putFragment(@NonNull Bundle bundle, @NonNull String key, @NonNull Fragment fragment) {

            }

            @Nullable
            @Override
            public Fragment getFragment(@NonNull Bundle bundle, @NonNull String key) {
                return null;
            }

            @NonNull
            @Override
            public List<Fragment> getFragments() {
                return null;
            }

            @Nullable
            @Override
            public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment f) {
                return null;
            }

            @Override
            public boolean isDestroyed() {
                return false;
            }

            @Override
            public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb, boolean recursive) {

            }

            @Override
            public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb) {

            }

            @Nullable
            @Override
            public Fragment getPrimaryNavigationFragment() {
                return null;
            }

            @Override
            public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {

            }

            @Override
            public boolean isStateSaved() {
                return false;
            }
        };

      LayoutInflater  li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
     WindowManager   wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                //WindowManager.LayoutParams.TYPE_INPUT_METHOD |
                LAYOUT_FLAG,// | WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE| WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
list.add("sfd");
        View myview = li.inflate(R.layout.youtubeplayer, null);
        params.gravity = Gravity.RIGHT | Gravity.TOP;
        Youtube_Player_Fragment youtubeFragment = Youtube_Player_Fragment.newInstance(list);
       popup.beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();


        wm.addView(myview, params);
    }
}
