package com.example.youtubebg.Views;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.youtubebg.Fragments.Youtube_Player_Fragment;
import com.example.youtubebg.Models.Video;
import com.example.youtubebg.Playlists.ViewModels.Youtube_Player_ViewModel;
import com.example.youtubebg.R;
import com.example.youtubebg.Service.Floating_Window_Service;
import com.example.youtubebg.ViewModels.Service_ViewModel;
import com.example.youtubebg.ViewModels.YoutubePlaylist_ViewModel;
import com.example.youtubebg.Playlists.Adapters.Play_Playlist_Adapter;

import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class YoutubePlayerr extends AppCompatActivity implements Play_Playlist_Adapter.adapterCallBack {

    private Play_Playlist_Adapter adapter;
    private RecyclerView recyclerView;
    private Youtube_Player_ViewModel youtube_player_viewModel;
    private Youtube_Player_Fragment youtube_player_fragment;
    private Observer<Video> observer;
    private List<String> ids = new LinkedList<>();
    private List<String> names = new LinkedList<>();


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.youtubeplayer);
        youtube_player_viewModel = ViewModelProviders.of(this).get(Youtube_Player_ViewModel.class);
        youtube_player_viewModel.createObservable((List<Video>) getIntent().getSerializableExtra("videos"));

        initRecycler();
        initObserver();
       initFragment();

 }

    @Override
    public void saveVideo(int position) {
        youtube_player_fragment.playVideo(position);
    }


    public void StartFloating(){
        Intent i = new Intent(YoutubePlayerr.this, Floating_Window_Service.class);
        Service_ViewModel.makeObservable(names);
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

    private void initObserver()
    {
observer = new Observer<Video>() {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Video s) {
names.add(s.getTitles());
ids.add(s.getId());
adapter.updateAdapter(names);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
};
    }
public void initRecycler()
{
    adapter = new Play_Playlist_Adapter(new LinkedList<String>(), this);
    recyclerView = findViewById(R.id.videos);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
}

public void initFragment()
{
    youtube_player_fragment = Youtube_Player_Fragment.newInstance(ids);
    Youtube_Player_Fragment youtubeFragment = youtube_player_fragment;
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.flYoutube, youtubeFragment).commit();
    youtube_player_fragment.playVideo(0);
}

}
