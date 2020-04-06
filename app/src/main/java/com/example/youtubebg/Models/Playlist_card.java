package com.example.youtubebg.Models;

import com.example.youtubebg.TypeConverter;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;

@Entity
public class Playlist_card {
    @ColumnInfo(name = "photo")
    private String photo;
    @ColumnInfo(name = "name")
    private String name;
    @TypeConverters(TypeConverter.class)
    @ColumnInfo(name = "videos")
    private List<String> videos;

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
}