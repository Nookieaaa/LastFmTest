package com.nookdev.lastfmtest.screens.albums.mvp;

import com.nookdev.lastfmtest.api.LastFmApi;
import com.nookdev.lastfmtest.models.DaoSession;

public class AlbumsActivityModel {
    private final LastFmApi api;
    private final DaoSession daoSession;

    public AlbumsActivityModel(LastFmApi api, DaoSession daoSession) {
        this.api = api;
        this.daoSession = daoSession;
    }
}
