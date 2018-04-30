package com.example.arvin.refactoringtomvp.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.example.arvin.refactoringtomvp.model.ArtistInteractor;
import com.example.arvin.refactoringtomvp.model.ArtistResponse;
import com.example.arvin.refactoringtomvp.view.ArtistView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ArtistPresenter {

    Context mContext;
    ArtistView view;
    private ArtistInteractor mArtistInteractor;

    public ArtistPresenter(ArtistInteractor mArtistInteractor , Context mContext){
        this.mArtistInteractor = mArtistInteractor;
        this.mContext = mContext;
}

    public void bind(ArtistView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    @SuppressLint("CheckResult")
    public void networkCall(){

        mArtistInteractor.getPopData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArtistResponse>() {

                    @Override
                    public void accept(ArtistResponse response) throws Exception {

                        if(view != null) {
                            view.updateUi(response.getResults());
                        }

                        Toast.makeText(mContext, response.getResults().get(0).getArtistName(), Toast.LENGTH_SHORT).show();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                    }
                });

    }



}
