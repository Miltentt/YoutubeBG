package com.example.youtubebg.ViewModels;

import com.example.youtubebg.Models.Video;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class YoutubePlaylist_ViewModel extends ViewModel {
    private List<String> names = new LinkedList<>();
    private ArrayList<String> id= new ArrayList<>();

public List<String> getNames(List<Video> videos)
{
    for(int i =0; i<videos.size();i++)
    {
        names.add(videos.get(i).getTitles());
    }

  return names;
}
    public ArrayList<String> getId(List<Video> videos, String idd)
    {
        id.add(idd);
        for(int i =0; i<videos.size();i++)
        {
            id.add(videos.get(i).getId());
        }

        return id;
    }
public ArrayList<String> NextVideo(List<Video> videos, String name) {
    id.clear();
    for (int i = 0; i < videos.size(); i++) {
        if (videos.get(i).getTitles().equals(name)) {
            for (int j = i; j < videos.size(); j++) {
                id.add(videos.get(j).getId());
            }
        }
    }
    return  id;
}
}
