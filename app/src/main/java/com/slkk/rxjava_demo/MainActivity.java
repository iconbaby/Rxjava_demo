package com.slkk.rxjava_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.slkk.rxjava_demo.bean.MovieEntity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Bind(R.id.result_TV)
    TextView resultTV;
    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.click_me_test)
    Button clickMeTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    private void getMovie() {
        Log.i(TAG, "getMovie: ");
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MovieService movieService = retrofit.create(MovieService.class);
//        movieService.getTopMovie(0, 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        new Subscriber<MovieEntity>() {
//                            @Override
//                            public void onCompleted() {
//                                Toast.makeText(MainActivity.this, "get top movie is complete", Toast.LENGTH_LONG).show();
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                resultTV.setText(e.getMessage());
//                            }
//
//                            @Override
//                            public void onNext(MovieEntity movieEntity) {
//                                resultTV.setText(movieEntity.toString());
//                            }
//                        });

        Subscriber<MovieEntity> subscriber = new Subscriber<MovieEntity>() {

            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "get move top is complete", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());

            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                resultTV.setText(movieEntity.toString());
            }
        };
        HttpMethod.getInstance().getMovieTop(subscriber, 0, 10);
    }

    @OnClick({R.id.click_me_BN, R.id.click_me_test})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click_me_BN:
                getMovie();
                break;
            case R.id.click_me_test:
                break;
        }
    }
}
