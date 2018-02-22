package com.room.whatevetv.mymovie.service;

import com.room.whatevetv.mymovie.entity.Base;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Samsung PC on 2/21/2018.
 */

public interface MovieService {

    @GET("search/movie")
    Call<Base> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/now_playing")
    Call<Base> nowPlaying(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/upcoming")
    Call<Base> upComing(@Query("api_key") String apiKey, @Query("query") String query);

}
