package com.example.youtubebg.Playlists.ViewModels;

import android.util.Log;

import com.example.youtubebg.Models.Video;
import com.example.youtubebg.Repository.Youtube_BG_Repository;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class Youtube_Player_ViewModel extends ViewModel {

    private Youtube_BG_Repository repository;
    private List<String> ids = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private Disposable disposable;
  private  MutableLiveData<List<String>> livedata = new MutableLiveData<List<String>>();;
    public Youtube_Player_ViewModel()
    {
        repository=Youtube_BG_Repository.getInstance();
    }

    public void createObservable(List<Video> videos)
    {
        if(disposable==null) {
            disposable = Flowable.fromIterable(videos)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSubscriber<Video>() {

                        @Override
                        public void onNext(Video video) {
                            names.add(video.getTitles());
                            ids.add(video.getId());
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onComplete() {
                            livedata.setValue(names);
                        }
                    });
        }
        }

public LiveData<List<String>> returNamesLiveData()
{
    return livedata;
}
public List<String> returnNames()
{
 return names;
}
    public List<String> returnIds()
    {
        return ids;
    }

    @Override
    public void onCleared() {
        disposable.dispose();
        super.onCleared();

    }


}


