package com.example.arvin.refactoringtomvp.model;

import io.reactivex.Observable;

public interface ArtistInteractor {
    Observable<ArtistResponse> getPopData();
}
