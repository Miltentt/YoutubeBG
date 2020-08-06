package com.example.youtubebg.Playlists.Views;

import com.example.youtubebg.Playlists.ViewModels.Playlists_SharedViewModel;
import com.example.youtubebg.Playlists.Adapters.Delete_Playlist_Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.FlowableSubscriber;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;

import java.util.LinkedList;
import java.util.List;

public class Fragment_Delete_Playlist extends Fragment implements Delete_Playlist_Adapter.callBack {
    RecyclerView recyclerView;
    private Playlists_SharedViewModel playlists_sharedViewModel;
    private Delete_Playlist_Adapter adapter;
    private FlowableSubscriber<List<Playlist_card>> observer;
    private List<Playlist_card> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.playlists,container,false);
        playlists_sharedViewModel = ViewModelProviders.of(getActivity()).get(Playlists_SharedViewModel.class);
        initRecycler(v);
        initObserver();
        return v;
    }


    public void initRecycler(View v) {
        adapter = new Delete_Playlist_Adapter(new LinkedList<Playlist_card>(), this);
        recyclerView = v.findViewById(R.id.Youtube_playlists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void openPlaylist(Playlist_card card) {
   playlists_sharedViewModel.deletePlaylist(card);
    }

    private void initObserver() {
       playlists_sharedViewModel.returnPlaylists().observe(this, new Observer<List<Playlist_card>>() {
           @Override
           public void onChanged(List<Playlist_card> playlist_cards) {
               adapter.updateList(playlist_cards);
           }
       });
    }

    @Override
    public void setEnterSharedElementCallback(@Nullable SharedElementCallback callback) {
        super.setEnterSharedElementCallback(callback);
    }
}


