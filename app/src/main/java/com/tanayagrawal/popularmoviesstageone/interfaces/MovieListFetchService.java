package com.tanayagrawal.popularmoviesstageone.interfaces;

import com.tanayagrawal.popularmoviesstageone.model.Results;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by tanayagrawal on 27/03/16.
 */
public interface MovieListFetchService {


    @GET("3/movie/{listType}?api_key=INSERT_YOUR_API_KEY_HERE")
    Call<Results> fetchMovies(@Path("listType") String listType);

}
