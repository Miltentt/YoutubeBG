package com.example.youtubebg.DataBase;

import com.example.youtubebg.Models.Playlist_card;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Playlist_card.class, version =1,exportSchema = false)
public abstract class Playlist_Database extends RoomDatabase {
    public abstract  Playlist_Dao playlist_dao();
}
