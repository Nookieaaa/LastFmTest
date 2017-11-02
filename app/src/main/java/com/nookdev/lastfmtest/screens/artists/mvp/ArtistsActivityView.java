package com.nookdev.lastfmtest.screens.artists.mvp;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.nookdev.lastfmtest.R;
import com.nookdev.lastfmtest.base.mvp.IView;
import com.nookdev.lastfmtest.models.Artist;
import com.nookdev.lastfmtest.screens.artists.ArtistsActivity;
import com.nookdev.lastfmtest.screens.artists.ArtistsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;


public class ArtistsActivityView extends FrameLayout implements IView, ArtistsAdapter.OnArtistSelected {
    FloatingActionButton fab;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ArtistsAdapter adapter;
    Spinner countrySelectionSpinner;
    PublishSubject<Artist> artistSelectionPublishSubject = PublishSubject.create();

    public ArtistsActivityView(@NonNull ArtistsActivity activity, Picasso picasso) {
        super(activity);
        inflate(activity, R.layout.activity_artists, this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);

        activity.setSupportActionBar(toolbar);

        countrySelectionSpinner = toolbar.findViewById(R.id.sp_country);

        recyclerView = findViewById(R.id.list_artists);
        swipeRefreshLayout = findViewById(R.id.swtrl_artists);

        LinearLayoutManager llm = new LinearLayoutManager(activity);
        adapter = new ArtistsAdapter(picasso, this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    public void updateList(List<Artist> data) {
        adapter.setData(data);
        if (!data.isEmpty())
            recyclerView.scrollToPosition(0);
    }

    public Observable<String> observeRefreshRequests() {
        return Observable.create(e -> {
            if (!e.isDisposed()) {
                swipeRefreshLayout.setOnRefreshListener(() -> {
                    if (!e.isDisposed())
                        e.onNext(new Object());
                });
            }
        }).withLatestFrom(observeCountryChanges(), (o, s) -> s);
    }

    public Observable<String> observeCountryChanges() {
        return Observable.create((ObservableOnSubscribe<String>) e -> {
            if (!e.isDisposed()) {
                countrySelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (!e.isDisposed()) {
                            String country = (String) countrySelectionSpinner.getItemAtPosition(position);
                            e.onNext(country);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }).startWith((String) countrySelectionSpinner.getSelectedItem());
    }

    public Observable<Artist> observeArtistSelection() {
        return artistSelectionPublishSubject;
    }

    @Override
    public void setLoading(boolean isLoading) {
        swipeRefreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void onArtistSelected(Artist artist) {
        artistSelectionPublishSubject.onNext(artist);
    }
}
