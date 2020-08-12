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
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Play_Playlist_SharedViewModel extends ViewModel {

    private Youtube_BG_Repository repository;
private LiveData<Playlist_card> songs_livedata;
private List<Video> videos = new LinkedList<>();
private Playlist_card card;
    public Play_Playlist_SharedViewModel ()
    {
        repository = Youtube_BG_Repository.getInstance();
    }


    public void createLiveData(int id)
    {
        songs_livedata = LiveDataReactiveStreams.fromPublisher(repository.getPlaylist(id)
               .doOnNext(playlist_card -> card=playlist_card)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()));
    }
public LiveData<Playlist_card> returnLiveData()
    {
        return songs_livedata;
    }
public void deletesong(int position)
{
    card.getVideos().remove(position);
    card.getNames().remove(position);
repository.updatePlaylist(card);
}

public List<Video> shufflePlaylist(int position)
{

        String name = card.getNames().get(position);
        String id = card.getVideos().get(position);
        card.getNames().remove(position);
        card.getVideos().remove(position);
        for (int i = 0; i < card.getNames().size(); i++) {
            videos.add(new Video(card.getNames().get(i), card.getVideos().get(i)));
        }
        Collections.shuffle(videos);
        videos.add(0, new Video(name, id));
    return videos;
}

public void removeVideos()
{
    videos.clear();
}



}
