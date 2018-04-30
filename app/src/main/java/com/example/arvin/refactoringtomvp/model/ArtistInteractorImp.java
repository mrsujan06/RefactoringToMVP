package com.example.arvin.refactoringtomvp.model;


import com.example.arvin.refactoringtomvp.model.api.ApiService;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistInteractorImp implements ArtistInteractor {

    ApiService mService;

    public ArtistInteractorImp() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(ApiService.class);

    }

    @Override
    public Observable<ArtistResponse> getPopData() {
        return mService.getPopData();
    }
}
