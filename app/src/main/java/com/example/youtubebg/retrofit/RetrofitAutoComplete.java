package com.example.youtubebg.retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAutoComplete  {

    private static RetrofitAutoComplete instance;

    private static  retrofit2.Retrofit retrofit;

        public RetrofitAutoComplete() {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://suggestqueries.google.com/complete/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            autoCompleteAPI = retrofit.create(AutoCompleteAPI.class);
        }
        public static RetrofitAutoComplete getInstance()
        {
            if(instance==null)
            {
                instance = new RetrofitAutoComplete();
            }
            return instance;

        }


        public static AutoCompleteAPI autoCompleteAPI;
}

