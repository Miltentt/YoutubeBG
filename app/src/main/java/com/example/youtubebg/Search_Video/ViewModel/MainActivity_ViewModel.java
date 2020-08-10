package com.example.youtubebg.Search_Video.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.Repository.Youtube_BG_Repository;


import javax.sql.DataSource;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity_ViewModel extends AndroidViewModel {
    private Youtube_BG_Repository repository;
    private Single<Search_Response> search_response;
private CompositeDisposable disposable;


    public MainActivity_ViewModel(Application application) {
super(application);
        repository = Youtube_BG_Repository.getInstance();
        repository.getRoomInstance(application);
    }


    public Single getObservable() {

        return search_response;
    }


    public void getSearch(String search)

    {
       search_response=  repository.getSearch(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
             search_response.subscribe(new SingleObserver<Search_Response>() {
                 @Override
                 public void onSubscribe(Disposable d) {
                     disposable.add(d);
                 }

                 @Override
                 public void onSuccess(Search_Response search_response) {

                 }

                 @Override
                 public void onError(Throwable e) {

                 }
             });

    }



}





