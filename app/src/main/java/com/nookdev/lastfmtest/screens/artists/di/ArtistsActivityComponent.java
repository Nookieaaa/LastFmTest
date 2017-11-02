package com.nookdev.lastfmtest.screens.artists.di;


import com.nookdev.lastfmtest.base.di.components.ApplicationComponent;
import com.nookdev.lastfmtest.screens.artists.ArtistsActivity;

import dagger.Component;

@Component(modules = ArtistsActivityModule.class, dependencies = ApplicationComponent.class)
@ArtistsActivityScope
public interface ArtistsActivityComponent {
    void inject(ArtistsActivity activity);
}
