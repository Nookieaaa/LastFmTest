package com.nookdev.lastfmtest.screens.albums.di;


import com.nookdev.lastfmtest.api.LastFmApi;
import com.nookdev.lastfmtest.models.DaoSession;
import com.nookdev.lastfmtest.screens.albums.AlbumsActivity;
import com.nookdev.lastfmtest.screens.albums.mvp.AlbumsActivityModel;
import com.nookdev.lastfmtest.screens.albums.mvp.AlbumsActivityPresenter;
import com.nookdev.lastfmtest.screens.albums.mvp.AlbumsActivityView;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@AlbumsActivityScope
@Module
public class AlbumsActivityModule {
    private AlbumsActivity activity;

    public AlbumsActivityModule(AlbumsActivity activity) {
        this.activity = activity;
    }

    @AlbumsActivityScope
    @Provides
    public AlbumsActivity provideAlbumsActivity() {
        return activity;
    }

    @AlbumsActivityScope
    @Provides
    public AlbumsActivityView provideView(AlbumsActivity activity, Picasso picasso) {
        return new AlbumsActivityView(activity, picasso);
    }

    @AlbumsActivityScope
    @Provides
    public AlbumsActivityPresenter providePresenter(AlbumsActivityView view, AlbumsActivityModel model) {
        return new AlbumsActivityPresenter(view, model);
    }

    @AlbumsActivityScope
    @Provides
    public AlbumsActivityModel provideModel(LastFmApi api, DaoSession daoSession) {
        return new AlbumsActivityModel(api, daoSession);
    }
}
