package com.example.youtubebg.retrofit;

import com.example.youtubebg.Models.Search_Response;

import java.util.Observable;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeAPI {
    @GET("search")
    Single<Search_Response> searchVideo(@Query("q") String query,
                                        @Query("type") String type,
                                        @Query("key") String key,
                                        @Query("part") String part,
                                        @Query("maxResults") String maxResults,
                                        @Query("pageToken") String pageToken);
}





