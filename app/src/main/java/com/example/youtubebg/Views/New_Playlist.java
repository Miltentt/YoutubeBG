package com.example.youtubebg.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubebg.R;

public class New_Playlist extends AppCompatActivity {
private EditText Playlist_Name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_playlist);
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("photo");
    }
    public void finish (View view)
    {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK);
        finish();


    }



}
