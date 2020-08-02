package com.example.youtubebg.retrofit;

import com.example.youtubebg.Models.Search_Response;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AutoCompleteAPI {
    @GET("search")
    Observable<String> searchAutoComplete(@Query("client") String client,
                                   @Query("q") String query,
                                   @Query("hl") String language);

}
