package com.nookdev.lastfmtest;

import android.app.Activity;
import android.app.Application;

import com.nookdev.lastfmtest.base.di.components.ApplicationComponent;
import com.nookdev.lastfmtest.base.di.components.DaggerApplicationComponent;
import com.nookdev.lastfmtest.base.di.modules.ContextModule;


public class App extends Application {
    private ApplicationComponent component;

    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public ApplicationComponent component() {
        return component;
    }
}
