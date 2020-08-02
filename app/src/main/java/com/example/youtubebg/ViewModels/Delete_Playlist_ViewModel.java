package com.example.youtubebg.ViewModels;

import android.app.Application;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;

public class Delete_Playlist_ViewModel extends AndroidViewModel {
        private Youtube_BG_Repository repository;

    public Delete_Playlist_ViewModel(@NonNull Application application) {

            super(application);
            repository = Youtube_BG_Repository.getInstance(application);
    }
    public void deletePlaylist(Playlist_card card)
    {
        repository.deletePlaylist(card);
    }
    public Single<List<Playlist_card>> loadPlaylists()
    {
        return repository.getPlaylists();
    }

}