package com.example.youtubebg.Search_Video.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.Playlists.Views.Playlists_MainActivity;
import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.ViewModel.MainActivity_ViewModel;
import com.example.youtubebg.Playlists.Views.Fragment_Playlists;
import com.example.youtubebg.Search_Video.Adapters.Search_Adapter;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements Fragment_search.searchresult {
    private RecyclerView recyclerView;
    private Search_Adapter adapter;
    private MainActivity_ViewModel mainActivity_viewModel;
    private SingleObserver<Search_Response> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity_viewModel = ViewModelProviders.of(this).get(MainActivity_ViewModel.class);
        initRecycler();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.playlist: {
                Intent i = new Intent(this, Playlists_MainActivity.class);
                startActivity(i);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void search(String search) {
        mainActivity_viewModel.getSearch(search);
      if(observer==null)
      {
          initObserver();
      }
        mainActivity_viewModel.getObservable().subscribe(observer);
    }




    private void initRecycler()
    {
        adapter = new Search_Adapter(new Search_Response().getItems(), this.getSupportFragmentManager(), this);
        recyclerView = findViewById(R.id.Youtube_Search_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }





    private void initObserver()
    {
        observer = new SingleObserver<Search_Response>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Search_Response search_response) {
                adapter.updateList(search_response.getItems());
            }

            @Override
            public void onError(Throwable e) {

            }
        };
    }



}


