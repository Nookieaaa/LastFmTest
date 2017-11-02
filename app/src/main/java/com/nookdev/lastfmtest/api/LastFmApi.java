package com.nookdev.lastfmtest.api;


import com.nookdev.lastfmtest.models.Album;
import com.nookdev.lastfmtest.models.ArtistsResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmApi {

    @GET("?method=geo.gettopartists")
    Single<ArtistsResponse> getTopArtists(@Query("country") String country);

    @GET(" /2.0/?method=artist.getinfo")
    Single<List<Album>> getArtistInfo(@Query("artist") String country);


}
