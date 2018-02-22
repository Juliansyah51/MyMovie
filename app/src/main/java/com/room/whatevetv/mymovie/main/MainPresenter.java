package com.room.whatevetv.mymovie.main;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.room.whatevetv.mymovie.entity.Base;
import com.room.whatevetv.mymovie.service.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Samsung PC on 2/21/2018.
 */

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void findMovies(String apiKey, String query) {
        view.showLoading();
        Retrofit.getMovieService().upComing(apiKey, query).enqueue(new Callback<Base>() {
            @Override
            public void onResponse(@NonNull Call<Base> call, @NonNull final Response<Base> response) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.hideLoading();
                        if (response.isSuccessful()) {
                            Base model = response.body();
                            if (model != null && model.getMovies() != null) {
                                if (model.getMovies().size() != 0) {
                                    view.onLoadMovieFinish(model.getMovies());
                                } else {
                                    view.onLoadEmptyMovie();
                                }
                            } else {
                                view.onLoadEmptyMovie();
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<Base> call, @NonNull final Throwable t) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        t.printStackTrace();
                        view.hideLoading();
                        view.onLoadMovieFailed("Terjadi kesalahan, Pesan " + t.getMessage());
                        view.onLoadEmptyMovie();
                    }
                });
            }
        });
    }

    public void search(String apiKey, String query) {
        view.showLoading();
        Retrofit.getMovieService().searchMovies(apiKey, query).enqueue(new Callback<Base>() {
            @Override
            public void onResponse(@NonNull Call<Base> call, @NonNull final Response<Base> response) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.hideLoading();
                        if (response.isSuccessful()) {
                            Base model = response.body();
                            if (model != null && model.getMovies() != null) {
                                if (model.getMovies().size() != 0) {
                                    view.onLoadMovieFinish(model.getMovies());
                                } else {
                                    view.onLoadEmptyMovie();
                                }
                            } else {
                                view.onLoadEmptyMovie();
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<Base> call, @NonNull final Throwable t) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        t.printStackTrace();
                        view.hideLoading();
                        view.onLoadMovieFailed("Terjadi kesalahan, Pesan " + t.getMessage());
                        view.onLoadEmptyMovie();
                    }
                });
            }
        });
    }
}
