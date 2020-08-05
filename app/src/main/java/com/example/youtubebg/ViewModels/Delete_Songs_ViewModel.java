package com.example.youtubebg.ViewModels;

import android.app.Application;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class Delete_Songs_ViewModel extends ViewModel {
    private Youtube_BG_Repository repository;
    public Delete_Songs_ViewModel() {


        repository = Youtube_BG_Repository.getInstance();
    }
    public void deletePlaylist(Playlist_card card)
    {
        repository.deletePlaylist(card);
    }
    public void updatePlaylist(Playlist_card card)
    {
        repository.addPlaylist(card);
    }
    public Flowable<List<Playlist_card>> loadPlaylists()
    {
        return repository.getPlaylists();
    }

}


