package com.example.youtubebg.Playlists.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.youtubebg.Playlists.ViewModels.Play_Playlist_SharedViewModel;
import com.example.youtubebg.Playlists.Adapters.Delete_Song_Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.R;

import java.util.LinkedList;

public class Fragment_Delete_Songs extends Fragment implements Delete_Song_Adapter.adapterCallBack {
    private Playlist_card playlist_cardd;
    private Delete_Song_Adapter adapter;
    private RecyclerView recyclerView;
    private Play_Playlist_SharedViewModel play_playlist_sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.play_playlist,container,false);
        initRecycler(v);
        play_playlist_sharedViewModel = ViewModelProviders.of(getActivity()).get(Play_Playlist_SharedViewModel.class);
        initObsever();
        return v;
    }


    @Override
    public void removeVideo(int position) {

        play_playlist_sharedViewModel.deletesong(position,playlist_cardd);

    }


    public void initRecycler(View v) {
        adapter = new Delete_Song_Adapter(new LinkedList<String>(), this);
        recyclerView = v.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void initObsever()
    {
        play_playlist_sharedViewModel.returnLiveData().observe(this, new Observer<Playlist_card>() {
            @Override
            public void onChanged(Playlist_card playlist_card) {
                adapter.updateAdapter(playlist_card.getNames());
               playlist_cardd=playlist_card;
            }
        });





    }


}