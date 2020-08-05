package com.example.youtubebg.Search_Video.ViewModel;

import android.app.Application;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class New_Playlist_ViewModel extends ViewModel {
    private Youtube_BG_Repository repository;
    public New_Playlist_ViewModel() {

        repository = Youtube_BG_Repository.getInstance();
    }

    public void createPlaylist(String name, String photo,String video, String namess )
    {
        List<String> vid = new LinkedList<>();
        vid.add(video);
        List<String> names = new LinkedList<>();
        names.add(namess);
        repository.addPlaylist(new Playlist_card(name,photo,vid,names));
    }





}
