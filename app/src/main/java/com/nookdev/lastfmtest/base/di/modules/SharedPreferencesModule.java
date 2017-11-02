package com.nookdev.lastfmtest.base.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.nookdev.lastfmtest.base.di.scopes.ApplicationScope;
import com.nookdev.lastfmtest.util.Constants;

import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module(includes = ContextModule.class)
public class SharedPreferencesModule {

    @Provides
    @ApplicationScope
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}