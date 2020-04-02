package com.example.youtubebg.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class Retrofit1 {

private static  Retrofit1 instance;
  private static  retrofit2.Retrofit retrofit;
    public Retrofit1() {
      retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
      youtubeApi = retrofit.create(YoutubeAPI.class);
    }
public Retrofit1 getInstance()
{
  if(instance==null)
  {
    instance = new Retrofit1();
  }
  return instance;

}


    public static YoutubeAPI youtubeApi;
}


