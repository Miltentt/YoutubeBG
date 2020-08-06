package com.example.youtubebg.Search_Video.Views;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtubebg.R;


public class Fragment_search extends Fragment {
    searchresult search;
    private EditText searchEditText;
    private ImageButton searchButton;
    public interface searchresult
    {
        public void search(String search);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        searchEditText = view.findViewById(R.id.search);
        searchButton = view.findViewById(R.id.imageButton);
        searchButton.setOnClickListener(e->searchh());

        return view;

    }

    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        search = (searchresult) activity;



    }

    public void searchh()
    {
        search.search(searchEditText.getText().toString());
    }



}