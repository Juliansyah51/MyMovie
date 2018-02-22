package com.room.whatevetv.mymovie.service;

import com.room.whatevetv.mymovie.BuildConfig;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Samsung PC on 2/21/2018.
 */

public class Retrofit {

    private static final retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_API)
            .callbackExecutor(Executors.newCachedThreadPool())
            .addConverterFactory(GsonConverterFactory.create())
            .client(setupOkHttpClient().build())
            .build();

    private static OkHttpClient.Builder setupOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.writeTimeout(10, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        httpClient.addInterceptor(logging);
        return httpClient;
    }

    private static final MovieService movieService = retrofit.create(MovieService.class);

    public static MovieService getMovieService() {
        return movieService;
    }
}
