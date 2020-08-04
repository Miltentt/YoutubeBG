package com.example.youtubebg.Search_Video.ViewModel;

import android.app.Application;

import com.example.youtubebg.Repository.Youtube_BG_Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class Playlist_DialogFragment_ViewModel extends ViewModel {

    private Youtube_BG_Repository repository;

    public Playlist_DialogFragment_ViewModel() {

        repository = Youtube_BG_Repository.getInstance();

    }







}
