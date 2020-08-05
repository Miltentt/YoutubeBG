package com.example.youtubebg.Playlists.ViewModels;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Playlists.Views.Playlists_MainActivity;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Playlists_SharedViewModel extends ViewModel {
private LiveData<List<Playlist_card>> livedata_playlists;
    private Youtube_BG_Repository repository;
    public Playlists_SharedViewModel()
    {
        repository = Youtube_BG_Repository.getInstance();
        livedata_playlists = LiveDataReactiveStreams.fromPublisher(repository.getPlaylists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));
    }

public LiveData<List<Playlist_card>> returnPlaylists()
{
    return  livedata_playlists;
}
    public void deletePlaylist(Playlist_card card)
    {
        repository.deletePlaylist(card);

    }





}
