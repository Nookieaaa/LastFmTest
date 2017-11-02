package com.nookdev.lastfmtest.screens.artists.mvp;


import android.app.Activity;
import android.content.Intent;

import com.nookdev.lastfmtest.api.LastFmApi;
import com.nookdev.lastfmtest.models.Artist;
import com.nookdev.lastfmtest.models.ArtistDao;
import com.nookdev.lastfmtest.models.ArtistsResponse;
import com.nookdev.lastfmtest.models.DaoSession;
import com.nookdev.lastfmtest.screens.albums.AlbumsActivity;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ArtistsActivityModel {
    private final LastFmApi api;
    private final DaoSession daoSession;
    private final Activity activity;

    public ArtistsActivityModel(Activity activity, LastFmApi api, DaoSession daoSession) {
        this.api = api;
        this.daoSession = daoSession;
        this.activity = activity;
    }

    public Single<List<Artist>> getArtists(String country) {
        return api.getTopArtists(country)
                .map(ArtistsResponse::getArtists)
                .subscribeOn(Schedulers.io())
                .map(list -> {
                    Collections.sort(list, (o1, o2) -> o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase()));
                    return list;
                })
                .retryWhen(throwableFlowable -> throwableFlowable.flatMapSingle(err -> {
                    ArtistDao dao = daoSession.getArtistDao();
                    return Single.just(dao.queryBuilder()
                            .list());
                }));
    }

    public void launchAlbumsActivity(Artist artist) {
        Intent intent = AlbumsActivity.getStartIntent(activity, artist);
        activity.startActivity(intent);
    }
}
