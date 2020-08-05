package com.example.youtubebg.Playlists.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Playlists.ViewModels.Playlists_SharedViewModel;
import com.example.youtubebg.R;
import com.example.youtubebg.ViewModels.Playlists_ViewModel;
import com.example.youtubebg.Views.Play_Playlist;
import com.example.youtubebg.adapters.Playlist_Adapter;

import org.reactivestreams.Subscription;

import java.util.LinkedList;
import java.util.List;

public class Fragment_Playlists extends Fragment implements Playlist_Adapter.callBack {
    RecyclerView recyclerView;
    private Playlists_SharedViewModel playlists_sharedViewModel;
    private Playlist_Adapter adapter;
    private FlowableSubscriber observer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.playlists,container,false);
        playlists_sharedViewModel = ViewModelProviders.of(this).get(Playlists_SharedViewModel.class);
        initObserver();
        adapter = new Playlist_Adapter(new LinkedList<Playlist_card>(), Fragment_Playlists.this);
        initRecycler(v);
        initObserver();
        return v;
    }




    public void initRecycler(View v) {

        recyclerView = v.findViewById(R.id.playlists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void openPlaylist(int id) {
        Intent i = new Intent(getContext(), Play_Playlist.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    private void initObserver() {
      playlists_sharedViewModel.returnPlaylists().observe(this, new Observer<List<Playlist_card>>() {
          @Override
          public void onChanged(List<Playlist_card> playlist_cards) {
              adapter.update(playlist_cards);
          }
      });
    }
}