package com.example.youtubebg.Search_Video.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.ViewModel.Fragment_Search_ViewModel;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Fragment_search extends Fragment {
    searchresult search;
private SearchView searchView;
private Fragment_Search_ViewModel fragment_search_viewModel;
    private CompositeDisposable disposables = new CompositeDisposable();
    private long timeSinceLastRequest; // for log printouts only. Not part of logic.
    public interface searchresult
    {
        public void search(String search);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.fragment_search,container,false);
    searchView = view.findViewById(R.id.searchview);
fragment_search_viewModel = ViewModelProviders.of(this).get(Fragment_Search_ViewModel.class);
fragment_search_viewModel.initiateObesrvable(searchView);
      initObserver();
        return view;

    }

    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        search = (searchresult) activity;



    }

public void searchh()
{

}
private void initObserver()
{
    fragment_search_viewModel.returnObservable().subscribe(new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            disposables.add(d);
        }
        @Override
        public void onNext(String s) {
            Log.i("xd", "onNext: time  since last request: " + (System.currentTimeMillis() - timeSinceLastRequest));
            Log.i("xd", "onNext: search query: " + s);
            timeSinceLastRequest = System.currentTimeMillis();
        }
        @Override
        public void onError(Throwable e) {
        }
        @Override
        public void onComplete() {
        }
    });
}


}



