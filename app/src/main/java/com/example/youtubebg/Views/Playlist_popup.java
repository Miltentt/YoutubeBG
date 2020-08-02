package com.example.youtubebg.Views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import com.example.youtubebg.Models.Playlist_card;
import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.R;
import com.example.youtubebg.Repository.Youtube_BG_Repository;
import com.example.youtubebg.adapters.Playlist_Adapter;
import com.example.youtubebg.adapters.Popup_adapter;
import com.example.youtubebg.adapters.Search_Adapter;

import java.util.LinkedList;
import java.util.List;

public class Playlist_popup extends AppCompatDialogFragment implements Popup_adapter.adapterCallBack {
    private Search_Response.Item item;
    private Popup_adapter adapter;
private RecyclerView recyclerView;
private Youtube_BG_Repository repository;
private Playlist_card playlist_card;
private List<String> videos= new LinkedList<>();
private List<String> titles = new LinkedList<>();
private SingleObserver observer;
    public Playlist_popup(Search_Response.Item item)
    {
this.item=item;
repository = Youtube_BG_Repository.getInstance(getContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.playlist_popup,null,false);

        builder.setView(view);




       initObserver();
       repository.getPlaylists().subscribe(observer);
        adapter = new Popup_adapter(new LinkedList<Playlist_card>());
       adapter.setCallBack(this);
        recyclerView = view.findViewById(R.id.playlists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

TextView text = view.findViewById(R.id.textView3);
text.setOnClickListener(e->newplaylist());


        return builder.create();
    }



public void newplaylist ()
{

    Intent i = new Intent(getActivity(), New_Playlist.class);
    i.putExtra("photo",item.getSnippet().getThumbnails().getDefault().getUrl());
    i.putExtra("id",item.getId().getVideoId());
    i.putExtra("names",item.getSnippet().getTitle());
    startActivity(i);

}

    @Override
    public void saveVideo(Playlist_card card) {
playlist_card = card;
videos=card.getVideos();
titles=card.getNames();
videos.add(item.getId().getVideoId());
titles.add(item.getSnippet().getTitle());
playlist_card.setNames(titles);
playlist_card.setVideos(videos);
repository.deletePlaylist(card);
repository.addPlaylist(playlist_card);
dismiss();
    }


    private void initObserver() {
        observer = new SingleObserver<List<Playlist_card>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Playlist_card> playlist_cards) {

                    adapter.update(playlist_cards);

            }

            @Override
            public void onError(Throwable e) {

            }
        };
    }
}
