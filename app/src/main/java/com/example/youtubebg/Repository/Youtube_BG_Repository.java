package com.example.youtubebg.Repository;

import android.content.Context;
import android.util.Log;

import com.example.youtubebg.DataBase.Playlist_Database;
import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.adapters.Search_Adapter;
import com.example.youtubebg.retrofit.Retrofit1;

import java.util.List;

import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Youtube_BG_Repository {

    private static  Youtube_BG_Repository instance;
   private Search_Response list = new Search_Response();
   private Retrofit1 retrofit1;
    private Playlist_Database db;
    private List<Playlist_card> playlists;
    private Playlist_card playlist;
    public static Youtube_BG_Repository getInstance()
    {
        if(instance==null)
        {
            instance = new Youtube_BG_Repository();
        }
        return instance;
    }
    public Youtube_BG_Repository(){
        retrofit1= Retrofit1.getInstance();

    }
    public Search_Response getSearch(String search)
    {
        Call<Search_Response> youtubeResponseCall = Retrofit1.youtubeApi.searchVideo(search, "video", "AIzaSyDtg9GVjWLW_KzJzyNPsMKTYOYD8YDrod8", "snippet,id", "10", "");
        youtubeResponseCall.enqueue(new Callback<Search_Response>() {
            @Override
            public void onResponse(Call<Search_Response> call, Response<Search_Response> response) {
                list = response.body();
                Log.i("xd","tja");
            }

            @Override
            public void onFailure(Call<Search_Response> call, Throwable t) {
                Log.i("xd","retro failure");
            }
        });


        return list;
    }
public List<Playlist_card> getPlaylists()
{
    playlists = db.playlist_dao().LoadAllPlaylists();
    return playlists;
}
public void AddPlaylist(Playlist_card playlist_card)
{
    db.playlist_dao().Insert(playlist_card);
}
public Playlist_card getPlaylist(int id)
{
   playlist= db.playlist_dao().LoadPlaylist(id);

   return  playlist;
}





}
