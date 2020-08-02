package com.example.youtubebg.Models;

import com.example.youtubebg.DataBase.TypeConverter;

import java.io.Serializable;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
public class Playlist_card implements Serializable {

    public Playlist_card(String name, String photo, List<String> videos,List<String> names)
    {
        this.name=name;
        this.photo=photo;
        this.videos=videos;
        this.names=names;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "photo")
    private String photo;
    @ColumnInfo(name = "name")
    private String name;
    @TypeConverters(TypeConverter.class)
    @ColumnInfo(name = "videos")
    private List<String> videos;
    @TypeConverters(TypeConverter.class)
    @ColumnInfo(name = "names")
    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}