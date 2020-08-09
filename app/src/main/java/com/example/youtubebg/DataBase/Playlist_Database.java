package com.example.youtubebg.DataBase;

import android.content.Context;

import com.example.youtubebg.Models.Playlist_card;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Playlist_card.class, version =1,exportSchema = false)
public abstract class Playlist_Database extends RoomDatabase {
    public abstract  Playlist_Dao playlist_dao();
    private static Playlist_Database INSTANCE;
   public static Playlist_Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Playlist_Database.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    Playlist_Database.class,
                                    "Playlists")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
