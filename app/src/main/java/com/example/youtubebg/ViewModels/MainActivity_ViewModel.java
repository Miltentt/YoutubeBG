package com.example.youtubebg.ViewModels;

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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity_ViewModel extends AndroidViewModel {
    private Youtube_BG_Repository repository;
    private Single Search_response;



    public MainActivity_ViewModel(Application application) {
super(application);
        repository = Youtube_BG_Repository.getInstance(application);
    }


    public Single getObservable() {

        return Search_response;
    }


    public void getSearch(String search)

    {
        Search_response =repository.getSearch(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}





