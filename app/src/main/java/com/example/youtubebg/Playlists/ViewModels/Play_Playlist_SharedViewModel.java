package com.example.youtubebg.Playlists.ViewModels;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Video;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Play_Playlist_SharedViewModel extends ViewModel {

    private Youtube_BG_Repository repository;
private LiveData<Playlist_card> songs_livedata;
private List<Video> videos = new LinkedList<>();
    public Play_Playlist_SharedViewModel ()
    {
        repository = Youtube_BG_Repository.getInstance();
    }


    public void createLiveData(int id)
    {
       songs_livedata = LiveDataReactiveStreams.fromPublisher(repository.getPlaylist(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));
    }
public LiveData<Playlist_card> returnLiveData()
    {
        return songs_livedata;
    }
public void deletesong(int position, Playlist_card playlist_card)
{
    playlist_card.getVideos().remove(position);
    playlist_card.getNames().remove(position);
repository.updatePlaylist(playlist_card);
}

public List<Video> shufflePlaylist(int position, Playlist_card playlist_card)
{
String name = playlist_card.getNames().get(position);
String id = playlist_card.getVideos().get(position);
playlist_card.getNames().remove(position);
playlist_card.getVideos().remove(position);
    for(int i=0;i<playlist_card.getNames().size();i++)
    {
        videos.add(new Video(playlist_card.getNames().get(i),playlist_card.getVideos().get(i)));
    }
Collections.shuffle(videos);
    videos.add(0,new Video(name,id));
return videos;
}





}
