package com.nookdev.lastfmtest.screens.artists.mvp;

import com.nookdev.lastfmtest.base.mvp.IPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ArtistsActivityPresenter implements IPresenter {

    ArtistsActivityView view;
    ArtistsActivityModel model;

    CompositeDisposable compositeDisposable;

    public ArtistsActivityPresenter(ArtistsActivityView view, ArtistsActivityModel model) {
        this.view = view;
        this.model = model;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate() {
        add(
                view.observeRefreshRequests()
                        .mergeWith(view.observeCountryChanges())
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(__ -> {
                            view.setLoading(true);
                        })
                        .observeOn(Schedulers.io())
                        .flatMapSingle(country ->
                                model.getArtists(country)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .doOnSuccess(list -> view.updateList(list)))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(__ -> {
                            view.setLoading(false);
                        }, err -> {
                            err.printStackTrace();
                            view.setLoading(false);
                        })
        );
        add(
                view.observeArtistSelection()
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(artist -> model.launchAlbumsActivity(artist))
                        .subscribe(__ -> {
                        }, Throwable::printStackTrace)
        );
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }


    void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
