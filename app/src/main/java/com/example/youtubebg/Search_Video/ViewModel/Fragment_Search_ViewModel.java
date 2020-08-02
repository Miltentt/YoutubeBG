package com.example.youtubebg.Search_Video.ViewModel;

import android.app.Application;

import com.example.youtubebg.Repository.Youtube_BG_Repository;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class Fragment_Search_ViewModel extends AndroidViewModel {

    private Youtube_BG_Repository repository;
private Observable<String> observableQueryText;



    public Fragment_Search_ViewModel(Application application)
    {
        super(application);
        repository = Youtube_BG_Repository.getInstance(application.getApplicationContext());

    }


public  void initiateObesrvable(SearchView searchView)
{
    observableQueryText = Observable
            .create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(final ObservableEmitter<String> emitter) throws Exception {

                    // Listen for text input into the SearchView
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(final String newText) {
                            if(!emitter.isDisposed()){
                                emitter.onNext(newText); // Pass the query to the emitter
                            }
                            return false;
                        }
                    });
                }
            })
            .debounce(1000, TimeUnit.MILLISECONDS) // Apply Debounce() operator to limit requests
            .subscribeOn(Schedulers.io());
}

public Observable<String> returnObservable()
{

    return observableQueryText;
}







}
