package com.example.arvin.refactoringtomvp.model.api;

import com.example.arvin.refactoringtomvp.model.ArtistResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //Pop
    @GET("search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    Observable<ArtistResponse> getPopData();

}
