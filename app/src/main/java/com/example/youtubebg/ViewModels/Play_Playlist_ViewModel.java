package com.example.youtubebg.ViewModels;

import android.util.Log;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Video;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class Play_Playlist_ViewModel extends ViewModel {
private  List<Video> videos = new LinkedList<>();


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
        String id="loool";
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getTitles().equals(name)) {
                id = videos.get(i).getId();
            }
        }
        Log.i("xd",id);
        return id;
    }
    public List<Video> shuffle(boolean shuffled) {
        if (shuffled == true) {
            Collections.shuffle(videos);
        } else {

        }
        return videos;
    }

}
