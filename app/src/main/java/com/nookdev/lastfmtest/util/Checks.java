package com.nookdev.lastfmtest.util;


import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public final class Checks {

    public static @NonNull
    <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> Observer<T> getDefaultObserver() {
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("RxJava", "OnSubscribe");
            }

            @Override
            public void onNext(T o) {
                Log.d("RxJava", "OnNext: " + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxJava", e.getLocalizedMessage(), e);
            }

            @Override
            public void onComplete() {
                Log.d("RxJava", "Completed");
            }
        };
    }

}
