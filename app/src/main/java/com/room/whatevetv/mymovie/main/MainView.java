package com.room.whatevetv.mymovie.main;

import com.room.whatevetv.mymovie.entity.Movie;

import java.util.List;

/**
 * Created by Samsung PC on 2/21/2018.
 */

public interface MainView {

    void showLoading();

    void hideLoading();

    void onLoadMovieFinish(List<Movie> movies);


    void onLoadMovieFailed(String message);

    void onLoadEmptyMovie();

}
