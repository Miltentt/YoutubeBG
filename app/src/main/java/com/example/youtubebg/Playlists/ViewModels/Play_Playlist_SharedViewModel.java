package com.example.youtubebg.Playlists.ViewModels;

import android.view.View;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Playlists.Views.Play_Playlist;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Play_Playlist_SharedViewModel extends ViewModel {

    private Youtube_BG_Repository repository;
private LiveData<Playlist_card> songs_livedata;
    public Play_Playlist_SharedViewModel ()
    {
        repository = Youtube_BG_Repository.getInstance();
    }


    public void createLiveData(int id)
    {
       songs_livedata = LiveDataReactiveStreams.fromPublisher(repository.getPlaylist(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));
    }
public LiveData<Playlist_card> returnLiveData()
    {
        return songs_livedata;
    }

}
