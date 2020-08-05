package com.example.youtubebg.ViewModels;

import android.app.Application;
import android.util.Log;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Video;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class Play_Playlist_ViewModel extends ViewModel {
private  List<Video> videos = new LinkedList<>();
    private Youtube_BG_Repository repository;

    public Play_Playlist_ViewModel() {

        repository = Youtube_BG_Repository.getInstance();

    }

public Flowable<Playlist_card> getPlaylist(int id)
{
  return repository.getPlaylist(id);

}

    public void populate(Playlist_card card)
    {

        for(int i =0;i<card.getNames().size();i++)
        {
            videos.add(new Video(card.getNames().get(i),card.getVideos().get(i)));
        }

    }

    public List<Video> switchfirst(String name)
    {
        for(int i =0;i<videos.size();i++)
        {
            if(videos.get(i).getTitles().equals(name))
            {
                videos.remove(i);
            }

        }

return videos;
    }
    public String findId(String name) {
        String id="";
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getTitles().equals(name)) {
                id = videos.get(i).getId();
            }
        }

        return id;
    }
    public String findName(String namee) {
        String name="";
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getTitles().equals(namee)) {
                name = videos.get(i).getTitles();
            }
        }

        return name;
    }
    public List<Video> shuffle(boolean shuffled) {
        if (shuffled == true) {
            Collections.shuffle(videos);
        } else {

        }
        return videos;
    }

}
