package com.slkk.rxjava_demo;

import com.slkk.rxjava_demo.bean.MovieEntity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dell on 2017/7/13.
 */

public class HttpMethod {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private MovieService movieService;
    private static final String baseUrl = "https://api.douban.com/v2/movie/";
    private static HttpMethod httpMethod = new HttpMethod();

    private HttpMethod() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieService = retrofit.create(MovieService.class);
    }

    public static HttpMethod getInstance() {
        return httpMethod;
    }

    public void getMovieTop(Subscriber<MovieEntity> subscriber, int start, int count) {
        movieService.getTopMovie(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
