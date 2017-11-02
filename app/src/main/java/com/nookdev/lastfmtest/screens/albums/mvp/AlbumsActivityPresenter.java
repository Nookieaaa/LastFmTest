package com.nookdev.lastfmtest.screens.albums.mvp;


import com.nookdev.lastfmtest.base.mvp.IPresenter;
import com.nookdev.lastfmtest.models.Artist;

public class AlbumsActivityPresenter implements IPresenter {
    private final AlbumsActivityView view;
    private final AlbumsActivityModel model;

    public AlbumsActivityPresenter(AlbumsActivityView view, AlbumsActivityModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    public void passArtist(Artist artist) {
        view.setTitle(artist.getName());

    }
}
