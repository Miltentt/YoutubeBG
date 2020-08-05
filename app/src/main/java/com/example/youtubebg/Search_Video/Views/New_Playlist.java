package com.example.youtubebg.Search_Video.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.youtubebg.R;
import com.example.youtubebg.Search_Video.ViewModel.New_Playlist_ViewModel;

public class New_Playlist extends AppCompatActivity {
private EditText Playlist_Name;
private New_Playlist_ViewModel new_playlist_viewModel;
private EditText name;
private String photo;
private String id;
private String title;

@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_playlist);
        Bundle extras = getIntent().getExtras();
         photo = extras.getString("photo");
        id = extras.getString("id");
        title = extras.getString("names");
        name = findViewById(R.id.grav);
        new_playlist_viewModel = ViewModelProviders.of(this).get(New_Playlist_ViewModel.class);
    }
    public void finish (View view)
    {
        if(name.getText().toString().equals(""))
        {
            Toast.makeText(this,"Insert the name of the playlist",Toast.LENGTH_LONG).show();
        }
        else {
            new_playlist_viewModel.createPlaylist(name.getText().toString(), photo, id, title);
            finish();
        }

    }
}
