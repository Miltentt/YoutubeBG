package com.example.youtubebg.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.MainActivity_ViewModel;
import com.example.youtubebg.Views.Fragment_search;
import com.example.youtubebg.Views.Playlists;
import com.example.youtubebg.adapters.Search_Adapter;
import com.example.youtubebg.retrofit.Retrofit1;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Fragment_search.searchresult {
    TextView text;
    Search_Response list = new Search_Response();
    RecyclerView recyclerView;
    private FragmentManager finalPopup;
    private TextView textView;
    private Search_Adapter adapter;
    private MainActivity_ViewModel mainActivity_viewModel;
    private SingleObserver<Search_Response> observer;



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity_viewModel = ViewModelProviders.of(this).get(MainActivity_ViewModel.class);
        finalPopup = this.getSupportFragmentManager();
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
                Intent i = new Intent(this, Playlists.class);
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
        adapter = new Search_Adapter(list.getItems(), finalPopup, getApplicationContext());
        recyclerView = findViewById(R.id.a);
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


