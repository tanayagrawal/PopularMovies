package com.tanayagrawal.popularmoviesstageone.helper;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.tanayagrawal.popularmoviesstageone.interfaces.MovieListFetchService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by tanayagrawal on 27/03/16.
 */
public class NetworkClient {

    private final String baseUrl = "https://api.themoviedb.org";


    private MovieListFetchService service;

    public NetworkClient(){
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(baseUrl)).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(MovieListFetchService.class);
    }

    public MovieListFetchService getService(){
        return service;
    }

}
