package com.example.youtubebg.ViewModels;

import com.example.youtubebg.Models.Search_Response;



import javax.sql.DataSource;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity_ViewModel extends ViewModel {

    private Search_Response list = new Search_Response();

Observable<Search_Response> search_responseObservable = Observable
        .just(list)
.subscribeOn(Schedulers.io())
.observeOn(AndroidSchedulers.mainThread());




private void getSearch(String search)
{

}




}
