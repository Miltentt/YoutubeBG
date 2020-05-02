package com.example.youtubebg.DataBase;

import com.example.youtubebg.Models.Playlist_card;

import java.util.List;

import androidx.room.Dao;
import androidx.room.*;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface Playlist_Dao {

    @Insert
    void Insert(Playlist_card... playlist_cards);

    @Query("SELECT * FROM Playlist_card")
    Observable<List<Playlist_card>> LoadAllPlaylists();

    @Query("SELECT * FROM Playlist_card WHERE id=:id")
    Single<Playlist_card> LoadPlaylist(int id);
    @Delete
    void Delete(Playlist_card... feats);
}
