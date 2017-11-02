package com.nookdev.lastfmtest.screens.artists.di;


import com.nookdev.lastfmtest.api.LastFmApi;
import com.nookdev.lastfmtest.models.DaoSession;
import com.nookdev.lastfmtest.screens.artists.ArtistsActivity;
import com.nookdev.lastfmtest.screens.artists.mvp.ArtistsActivityModel;
import com.nookdev.lastfmtest.screens.artists.mvp.ArtistsActivityPresenter;
import com.nookdev.lastfmtest.screens.artists.mvp.ArtistsActivityView;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@ArtistsActivityScope
@Module
public class ArtistsActivityModule {
    private ArtistsActivity activity;

    public ArtistsActivityModule(ArtistsActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ArtistsActivityScope
    public ArtistsActivity provideArtistsActivity() {
        return activity;
    }

    @Provides
    @ArtistsActivityScope
    public ArtistsActivityView provideView(ArtistsActivity activity, Picasso picasso) {
        return new ArtistsActivityView(activity, picasso);
    }

    @Provides
    @ArtistsActivityScope
    public ArtistsActivityPresenter providePresenter(ArtistsActivityView view, ArtistsActivityModel model) {
        return new ArtistsActivityPresenter(view, model);
    }

    @Provides
    @ArtistsActivityScope
    public ArtistsActivityModel provideModel(ArtistsActivity activity, LastFmApi api, DaoSession daoSession) {
        return new ArtistsActivityModel(activity, api, daoSession);
    }
}
