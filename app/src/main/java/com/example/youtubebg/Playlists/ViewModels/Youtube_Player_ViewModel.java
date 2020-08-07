package com.example.youtubebg.Playlists.ViewModels;

import com.example.youtubebg.Models.Video;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import org.reactivestreams.Subscriber;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.MainThread;
import androidx.lifecycle.ViewModel;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Youtube_Player_ViewModel extends ViewModel {

    private Youtube_BG_Repository repository;
    private Flowable<Video> observable;
public Youtube_Player_ViewModel()
    {
        repository=Youtube_BG_Repository.getInstance();
    }

    public void createObservable(List<Video> videos)
    {
         observable = Flowable.fromIterable(videos)

        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
public Flowable<Video> returVideos()
{
    return observable;
}

    public List<String> sendVideos(List<Video> videos)
    {
        List<String> ids = new LinkedList<>();
        for(int i =0;i<videos.size();i++)
        {
            ids.add(videos.get(i).getId());
        }
        return ids;
    }

}


