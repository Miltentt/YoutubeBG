package com.example.youtubebg.ViewModels;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Video;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class Play_Playlist_ViewModel extends ViewModel {



    public List<Video> shuffle(Playlist_card card, boolean shuffled)
    {
        List<Video> videos = new LinkedList<>();
        for(int i =0;i<card.getNames().size();i++)
        {
            videos.add(new Video(card.getNames().get(i),card.getVideos().get(i)));
        }
if(shuffled==true)
{
    Collections.shuffle(videos);
}
else
{

}
return videos;
    }

    public List<Video> switchfirst(String name)
    {
        
    }

}
