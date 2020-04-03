package com.example.youtubebg.ViewModels;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.Repository.Youtube_BG_Repository;


import javax.sql.DataSource;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity_ViewModel extends ViewModel {
private Youtube_BG_Repository repository;
    private Search_Response list = new Search_Response();
public MainActivity_ViewModel() {


        repository = Youtube_BG_Repository.getInstance();

    }
    public Observable getObservable() {
        Observable<Search_Response> search_responseObservable = Observable
                .just(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return search_responseObservable;
}


public void getSearch(String search)
{
list =repository.getSearch(search);
}




}
