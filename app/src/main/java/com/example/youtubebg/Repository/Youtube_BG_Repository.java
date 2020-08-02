package com.example.youtubebg.Repository;

import android.content.Context;

import com.example.youtubebg.DataBase.Playlist_Database;
import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.retrofit.Retrofit1;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Youtube_BG_Repository {

    private static  Youtube_BG_Repository instance;
   private Retrofit1 retrofit1;
    private Playlist_Database db;
    private List<Playlist_card> playlists;
    private Playlist_card playlist;
    private boolean retro;

    public static Youtube_BG_Repository getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new Youtube_BG_Repository(context);
        }
        return instance;
    }
    public Youtube_BG_Repository(Context context) {
        retrofit1 = Retrofit1.getInstance();
        db = Playlist_Database.getDatabase(context);
    }

    // Retrofit
    public Single getSearch(String search)
    {
        return  Retrofit1.youtubeApi.searchVideo(search, "video", "AIzaSyDtg9GVjWLW_KzJzyNPsMKTYOYD8YDrod8", "snippet,id", "10", "");
    }


    //  Room Database
public Single<List<Playlist_card>> getPlaylists()
{

    return db.playlist_dao().LoadAllPlaylists()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
}
public void addPlaylist(Playlist_card playlist_card)
{
    db.playlist_dao().Insert(playlist_card);
}
public Single<Playlist_card> getPlaylist(int id)
{
   return  db.playlist_dao().LoadPlaylist(id)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread());
}

public void deletePlaylist(Playlist_card playlist_card)
{
    db.playlist_dao().Delete(playlist_card);
}




}
