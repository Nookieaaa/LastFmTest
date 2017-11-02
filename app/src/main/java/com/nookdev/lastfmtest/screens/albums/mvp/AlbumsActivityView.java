package com.nookdev.lastfmtest.screens.albums.mvp;


import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.nookdev.lastfmtest.R;
import com.nookdev.lastfmtest.base.mvp.IView;
import com.nookdev.lastfmtest.screens.albums.AlbumsActivity;
import com.nookdev.lastfmtest.screens.albums.AlbumsAdapter;
import com.squareup.picasso.Picasso;

public class AlbumsActivityView extends FrameLayout implements IView {
    Toolbar toolbar;
    AlbumsAdapter adapter;
    RecyclerView albumsList;


    public AlbumsActivityView(@NonNull AlbumsActivity activity, @NonNull Picasso picasso) {
        super(activity);
        inflate(activity, R.layout.activity_albums, this);

        toolbar = findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        ActionBar ab = activity.getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

        albumsList = findViewById(R.id.list_albums);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        adapter = new AlbumsAdapter(picasso);
        albumsList.setLayoutManager(llm);
        albumsList.setAdapter(adapter);

    }

    public void setTitle(String text) {
        toolbar.setTitle(text);
    }

    @Override
    public void setLoading(boolean isLoading) {

    }
}
