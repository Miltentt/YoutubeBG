package com.example.youtubebg.Views;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.youtubebg.Fragments.Youtube_Player_Fragment;
import com.example.youtubebg.Models.Video;
import com.example.youtubebg.R;
import com.example.youtubebg.Service.Floating_Window_Service;
import com.example.youtubebg.ViewModels.Service_ViewModel;
import com.example.youtubebg.ViewModels.YoutubePlaylist_ViewModel;
import com.example.youtubebg.adapters.Play_Playlist_Adapter;

import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class YoutubePlayerr extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {

    private Play_Playlist_Adapter adapter;
    private RecyclerView recyclerView;
    private YoutubePlaylist_ViewModel youtubePlaylist_viewModel;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.youtubeplayer);


        youtubePlaylist_viewModel = ViewModelProviders.of(this).get(YoutubePlaylist_ViewModel.class);

        Youtube_Player_Fragment youtubeFragment = Youtube_Player_Fragment.newInstance(youtubePlaylist_viewModel.getId((List<Video>) getIntent().getSerializableExtra("videos"),getIntent().getStringExtra("id")));
      getSupportFragmentManager().beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();

        adapter = new Play_Playlist_Adapter(youtubePlaylist_viewModel.getNames((List<Video>) getIntent().getSerializableExtra("videos")), this);
        recyclerView = findViewById(R.id.videos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

 }

    @Override
    public void saveVideo(String name) {

        Youtube_Player_Fragment youtubeFragment = Youtube_Player_Fragment.newInstance(youtubePlaylist_viewModel.NextVideo((List<Video>) getIntent().getSerializableExtra("videos"),name));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flYoutube, youtubeFragment).commit();
    }


    public void StartFloating(){
        Intent i = new Intent(YoutubePlayerr.this, Floating_Window_Service.class);
        List<String> names = new LinkedList<>();
        names.add(getIntent().getStringExtra("first"));
        List<Video> list = (List<Video>) getIntent().getSerializableExtra("videos");
        for(int j = 0; j<list.size(); j++)
        {
            names.add(youtubePlaylist_viewModel.getNames((List<Video>) getIntent().getSerializableExtra("videos")).get(j));
        }
        Service_ViewModel.makeObservable(names);
        List<String> ids = new LinkedList<>();
       ids = youtubePlaylist_viewModel.getId((List<Video>)getIntent().getSerializableExtra("videos"),getIntent().getStringExtra("id"));
        Service_ViewModel.makeObservableI(ids);
        startService(i);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.background_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.background: {
if(isMyServiceRunning(Floating_Window_Service.class)==false) {
    StartFloating();
}
else
{
     Intent a = new Intent(YoutubePlayerr.this, Floating_Window_Service.class);
    stopService(a);
}
                break;
            }


        }
        return super.onOptionsItemSelected(item);
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
