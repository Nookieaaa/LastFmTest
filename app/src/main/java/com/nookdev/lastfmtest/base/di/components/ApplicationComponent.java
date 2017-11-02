package com.nookdev.lastfmtest.base.di.components;

import com.nookdev.lastfmtest.api.LastFmApi;
import com.nookdev.lastfmtest.base.di.modules.ApiModule;
import com.nookdev.lastfmtest.base.di.modules.DatabaseModule;
import com.nookdev.lastfmtest.base.di.modules.PicassoModule;
import com.nookdev.lastfmtest.base.di.scopes.ApplicationScope;
import com.nookdev.lastfmtest.models.DaoSession;
import com.squareup.picasso.Picasso;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApiModule.class, PicassoModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    Picasso getPicasso();

    LastFmApi getApiService();

    DaoSession getDaoSession();
}
