package com.example.youtubebg.Playlists.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Playlists.ViewModels.Play_Playlist_SharedViewModel;
import com.example.youtubebg.R;
import com.example.youtubebg.Playlists.Adapters.Play_Playlist_Adapter;

import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.FlowableSubscriber;

public class Play_Playlist extends Fragment implements Play_Playlist_Adapter.adapterCallBack {
    private Play_Playlist_Adapter adapter;
    private RecyclerView recyclerView;
    private Playlist_card card;
    private FlowableSubscriber observer;
    private int id;
    Play_Playlist_SharedViewModel play_playlist_sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.play_playlist,container,false);
        initRecycler(v);
        play_playlist_sharedViewModel = ViewModelProviders.of(getActivity()).get(Play_Playlist_SharedViewModel.class);
        initObserver();
        initRecycler(v);
        return v;
    }

/*
    @Override
    public void saveVideo(String name) {
        Intent i = new Intent(this, YoutubePlayerr.class);
        play_playlist_sharedViewModel.populate(card);
        i.putExtra("id", play_playlist_sharedViewModel.findId(name));
        i.putExtra("first", play_playlist_sharedViewModel.findName(name));
        play_playlist_sharedViewModel.switchfirst(name);
        i.putExtra("videos", (Serializable) play_playlist_sharedViewModel.shuffle(true));
        startActivity(i);
    }
    */
@Override
public void saveVideo(String name) {
}


public void initRecycler(View v)
{
    adapter = new Play_Playlist_Adapter(new LinkedList<String>(), this);
    recyclerView = v.findViewById(R.id.recycler);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

}

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        play_playlist_sharedViewModel.getPlaylist(id).subscribe(observer);
    }
*/

    private void initObserver() {
        play_playlist_sharedViewModel.returnLiveData().observe(this, new Observer<Playlist_card>() {
            @Override
            public void onChanged(Playlist_card playlist_card) {
                adapter.updateAdapter(playlist_card.getNames());
            }
        });
    }


}