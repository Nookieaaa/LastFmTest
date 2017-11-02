package com.nookdev.lastfmtest.screens.albums.di;


import com.nookdev.lastfmtest.base.di.components.ApplicationComponent;
import com.nookdev.lastfmtest.screens.albums.AlbumsActivity;

import dagger.Component;

@AlbumsActivityScope
@Component(modules = AlbumsActivityModule.class, dependencies = ApplicationComponent.class)
public interface AlbumsActivityComponent {
    void inject(AlbumsActivity activity);
}
