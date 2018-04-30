package com.example.arvin.refactoringtomvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.refactoringtomvp.R;
import com.example.arvin.refactoringtomvp.adapter.ArtistAdapter;
import com.example.arvin.refactoringtomvp.model.ArtistInteractor;
import com.example.arvin.refactoringtomvp.model.ArtistInteractorImp;
import com.example.arvin.refactoringtomvp.model.ArtistResponse;
import com.example.arvin.refactoringtomvp.model.Result;
import com.example.arvin.refactoringtomvp.model.api.ApiService;
import com.example.arvin.refactoringtomvp.presenter.ArtistPresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ArtistView{

    ArtistInteractor mArtistInteractor;
    ArtistPresenter mArtistPresenter;
    RecyclerView mRecyclerView;
    ArtistAdapter mAdapter;
    ApiService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArtistInteractor = new ArtistInteractorImp();
        mArtistPresenter = new ArtistPresenter(mArtistInteractor , this );
        mArtistPresenter.bind(this);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mArtistPresenter.networkCall();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mArtistPresenter.unbind();
}


    @Override
    public void updateUi(List<Result> results) {
        mAdapter = new ArtistAdapter(results , this);
        mRecyclerView.setAdapter(mAdapter);

    }
}
