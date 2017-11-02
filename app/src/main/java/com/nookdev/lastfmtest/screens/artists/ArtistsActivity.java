package com.nookdev.lastfmtest.screens.artists;

import android.view.View;

import com.nookdev.lastfmtest.App;
import com.nookdev.lastfmtest.base.BaseActivity;
import com.nookdev.lastfmtest.base.mvp.IPresenter;
import com.nookdev.lastfmtest.base.mvp.IView;
import com.nookdev.lastfmtest.screens.artists.di.ArtistsActivityModule;
import com.nookdev.lastfmtest.screens.artists.di.DaggerArtistsActivityComponent;
import com.nookdev.lastfmtest.screens.artists.mvp.ArtistsActivityPresenter;
import com.nookdev.lastfmtest.screens.artists.mvp.ArtistsActivityView;

import javax.inject.Inject;

public class ArtistsActivity extends BaseActivity {

    @Inject
    ArtistsActivityView view;

    @Inject
    ArtistsActivityPresenter presenter;

    @Override
    protected View specifyLayout() {
        return view;
    }

    @Override
    protected void performInjection() {
        DaggerArtistsActivityComponent.builder()
                .applicationComponent(App.get(this).component())
                .artistsActivityModule(new ArtistsActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected IView specifyView() {
        return view;
    }

    @Override
    protected IPresenter specifyPresenter() {
        return presenter;
    }

}
