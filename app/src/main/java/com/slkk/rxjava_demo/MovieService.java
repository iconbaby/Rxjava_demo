package com.slkk.rxjava_demo;

import com.slkk.rxjava_demo.bean.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dell on 2017/7/13.
 */

public interface MovieService {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("conut") int count);

}