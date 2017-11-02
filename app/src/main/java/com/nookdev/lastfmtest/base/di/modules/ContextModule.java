package com.nookdev.lastfmtest.base.di.modules;

import android.content.Context;

import com.nookdev.lastfmtest.base.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module
public class ContextModule {
    private final Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return mContext;
    }
}
