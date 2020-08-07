package com.example.youtubebg.Search_Video.ViewModel;

import android.app.Application;
import android.util.Log;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Playlist_DialogFragment_ViewModel extends ViewModel {

    private Youtube_BG_Repository repository;

    public Playlist_DialogFragment_ViewModel() {

        repository = Youtube_BG_Repository.getInstance();

    }

public Flowable<List<Playlist_card>> returnPlaylists()
{
    return repository.getPlaylists().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

}

public void addSongToPlaylist(Search_Response.Item item, Playlist_card card)
{
    card.getVideos().add(item.getId().getVideoId());
    card.getNames().add(item.getSnippet().getTitle());
    repository.updatePlaylist(card);

}


}
