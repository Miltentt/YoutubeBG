package com.example.youtubebg.ViewModels;

import com.example.youtubebg.Models.Video;

import java.util.List;


import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Service_ViewModel  {

public static Observable<List<String>> observableNames;
    public static Observable<List<String>> observableId;

public static void makeObservable(List<String> list) {
    observableNames = Observable
            .just(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
}

public static Observable getObservableNames()
{



    return observableNames;
}

    public static void makeObservableI(List<String> list) {
        observableId = Observable
                .just(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable getObservableID()
    {



        return observableId;
    }


}
