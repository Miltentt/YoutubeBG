package com.example.youtubebg.ViewModels;

import android.app.Application;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Repository.Youtube_BG_Repository;
import com.example.youtubebg.Views.New_Playlist;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class New_Playlist_ViewModel extends AndroidViewModel {
    private Youtube_BG_Repository repository;
    public New_Playlist_ViewModel(@NonNull Application application) {
        super(application);
        repository = Youtube_BG_Repository.getInstance(application);
    }

    public void createPlaylist(String name, String photo,String video )
    {
        List<String> vid = new LinkedList<>();
        vid.add(video);
       repository.addPlaylist(new Playlist_card(name,photo,vid));

    }





}
