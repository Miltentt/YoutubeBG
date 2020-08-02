package com.example.youtubebg.retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public  class RetrofitYoutube {

private static RetrofitYoutube instance;
  private static  retrofit2.Retrofit retrofit;
    public RetrofitYoutube() {
      retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
      youtubeApi = retrofit.create(YoutubeAPI.class);
    }
public static RetrofitYoutube getInstance()
{
  if(instance==null)
  {
    instance = new RetrofitYoutube();
  }
  return instance;

}


    public static YoutubeAPI youtubeApi;
}




