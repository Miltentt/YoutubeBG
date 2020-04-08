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

import com.example.youtubebg.Models.Search_Response;
import com.example.youtubebg.R;

public class Playlist_popup extends AppCompatDialogFragment {
    private Search_Response.Item item;
    public Playlist_popup(Search_Response.Item item)
    {
this.item=item;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_to_playlist_dialog,null,false);

        builder.setView(view)
                .setTitle("Playlists");
TextView text = view.findViewById(R.id.textView3);
text.setOnClickListener(e->newplaylist());


        return builder.create();
    }



public void newplaylist ()
{

    Intent i = new Intent(getActivity(), New_Playlist.class);
    i.putExtra("photo",item.getSnippet().getThumbnails().getDefault().getUrl());
    i.putExtra("id",item.getId().getVideoId());
    startActivity(i);

}

}
