package com.example.youtubebg.Models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Video implements Serializable {

 private String title;
 private String id;
    public Video(String title, String id)
    {
        this.id=id;
        this.title=title;
    }


    public String getTitles() {
        return title;
    }

    public void setTitles(String titles) {
        this.title = titles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
