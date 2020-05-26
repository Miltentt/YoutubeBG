package com.example.youtubebg.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.youtubebg.R;

public class Search_Popup extends AppCompatDialogFragment {

   private EditText search;
   private ImageButton button;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_search,null,false);

        builder.setView(view)
                .setTitle("Playlists");
      search = view.findViewById(R.id.search);
      button = view.findViewById(R.id.imageButton);


        return builder.create();
    }

    public void finish()
    {
        


    }



}
