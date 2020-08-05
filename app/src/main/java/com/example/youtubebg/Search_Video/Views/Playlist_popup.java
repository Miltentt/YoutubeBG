package com.example.youtubebg.Search_Video.Views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.FlowableSubscriber;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.ViewModel.Playlist_DialogFragment_ViewModel;
import com.example.youtubebg.Search_Video.Adapters.Popup_adapter;

import org.reactivestreams.Subscription;

import java.util.LinkedList;
import java.util.List;

public class Playlist_popup extends AppCompatDialogFragment implements Popup_adapter.adapterCallBack {
    private Search_Response.Item item;
    private Popup_adapter adapter;
private RecyclerView recyclerView;
private Playlist_DialogFragment_ViewModel playlist_dialogFragment_viewModel;
private FlowableSubscriber<List<Playlist_card>> observer;

    public Playlist_popup(Search_Response.Item item)
    {
this.item=item;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.playlist_popup,null,false);
        recyclerView = view.findViewById(R.id.playlists);
        builder.setView(view);
        initObserver();
        initRecycler();
        playlist_dialogFragment_viewModel = ViewModelProviders.of(this).get(Playlist_DialogFragment_ViewModel.class);
       playlist_dialogFragment_viewModel.returnPlaylists().subscribeWith(observer);
TextView create_new_playlist = view.findViewById(R.id.textView3);
create_new_playlist.setOnClickListener(e->newplaylist());
        return builder.create();
    }


    public void initRecycler()
    {
        Log.i("xd","recycler");
        adapter = new Popup_adapter(new LinkedList<Playlist_card>());
        adapter.setCallBack(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }







public void newplaylist ()
{

    Intent i = new Intent(getActivity(), New_Playlist.class);
    i.putExtra("photo",item.getSnippet().getThumbnails().getDefault().getUrl());
    i.putExtra("id",item.getId().getVideoId());
    i.putExtra("names",item.getSnippet().getTitle());
    startActivityForResult(i,1);
    dismiss();

}

    @Override
    public void saveVideo(Playlist_card card) {
playlist_dialogFragment_viewModel.addSongToPlaylist(item,card);
dismiss();
    }


    private void initObserver() {
        Log.i("xd","observer");
        observer = new FlowableSubscriber<List<Playlist_card>>() {


            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<Playlist_card> playlist_cards) {
                adapter.update(playlist_cards);
                Log.i("xd","got it");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("xd","xdddd");
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
