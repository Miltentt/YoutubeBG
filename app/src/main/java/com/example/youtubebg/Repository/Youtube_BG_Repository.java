package com.example.youtubebg.Repository;

import android.content.Context;

import com.example.youtubebg.DataBase.Playlist_Database;
import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.retrofit.RetrofitYoutube;

import java.util.List;

import androidx.room.RoomDatabase;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Youtube_BG_Repository {

    private static  Youtube_BG_Repository instance;
    private RetrofitYoutube retrofitYoutube;

    private Playlist_Database db;

    public static Youtube_BG_Repository getInstance()
    {
        if(instance==null)
        {
            instance = new Youtube_BG_Repository();
        }
        return instance;
    }

    public Youtube_BG_Repository() {
        retrofitYoutube = RetrofitYoutube.getInstance();
    }

    // Retrofit
    public Single getSearch(String search)
    {
        return  RetrofitYoutube.youtubeApi.searchVideo(search, "video", "AIzaSyDtg9GVjWLW_KzJzyNPsMKTYOYD8YDrod8", "snippet,id", "20", "");
    }



    //  Room Database
public Single<List<Playlist_card>> getPlaylists()
{

    return db.playlist_dao().LoadAllPlaylists()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
}

public RoomDatabase getRoomInstance(Context context)
{
    db=Playlist_Database.getDatabase(context);
    return db;

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
