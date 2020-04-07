package com.example.youtubebg.DataBase;

import com.example.youtubebg.Models.Playlist_card;

import java.util.List;

import androidx.room.Dao;
import androidx.room.*;

@Dao
public interface Playlist_Dao {

    @Insert
    void Insert(Playlist_card... playlist_cards);

    @Query("SELECT * FROM Playlist_card")
    List<Playlist_card> LoadAllPlaylists();

    @Query("SELECT * FROM Playlist_card WHERE id=:id")
    Playlist_card LoadPlaylist(int id);
    @Delete
    void Delete(Playlist_card... feats);

}
