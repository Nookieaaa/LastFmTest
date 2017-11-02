package com.nookdev.lastfmtest.screens.albums;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nookdev.lastfmtest.App;
import com.nookdev.lastfmtest.base.BaseActivity;
import com.nookdev.lastfmtest.base.mvp.IPresenter;
import com.nookdev.lastfmtest.base.mvp.IView;
import com.nookdev.lastfmtest.models.Artist;
import com.nookdev.lastfmtest.screens.albums.di.AlbumsActivityModule;
import com.nookdev.lastfmtest.screens.albums.di.DaggerAlbumsActivityComponent;
import com.nookdev.lastfmtest.screens.albums.mvp.AlbumsActivityPresenter;
import com.nookdev.lastfmtest.screens.albums.mvp.AlbumsActivityView;

import org.parceler.Parcels;

import javax.inject.Inject;

public class AlbumsActivity extends BaseActivity {

    public static String EXTRA_ARTIST = "artist";

    @Inject
    AlbumsActivityView view;

    @Inject
    AlbumsActivityPresenter presenter;

    public static Intent getStartIntent(Context context, Artist artist) {
        Intent intent = new Intent(context, AlbumsActivity.class);
        intent.putExtra(EXTRA_ARTIST, Parcels.wrap(artist));
        return intent;
    }

    @Override
    protected View specifyLayout() {
        return view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Artist artist = null;
        if (intent.hasExtra(EXTRA_ARTIST))
            artist = Parcels.unwrap(intent.getParcelableExtra(EXTRA_ARTIST));
        presenter.passArtist(artist);
    }

    @Override
    protected void performInjection() {
        DaggerAlbumsActivityComponent.builder()
                .applicationComponent(App.get(this).component())
                .albumsActivityModule(new AlbumsActivityModule(this))
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
