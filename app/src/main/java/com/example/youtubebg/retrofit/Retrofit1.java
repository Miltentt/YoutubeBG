package com.example.youtubebg.retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public  class Retrofit1 {
  public static  retrofit2.Retrofit retrofit;
    public Retrofit1() {
      retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
      youtubeApi = retrofit.create(YoutubeAPI.class);
    }



    public static YoutubeAPI youtubeApi;
}


