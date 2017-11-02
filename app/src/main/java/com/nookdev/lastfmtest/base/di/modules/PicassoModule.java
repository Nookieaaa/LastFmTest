package com.nookdev.lastfmtest.base.di.modules;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.nookdev.lastfmtest.base.di.scopes.ApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = {ContextModule.class, NetworkModule.class})
public class PicassoModule {

    @Provides
    @ApplicationScope
    public Picasso providePicasso(Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @ApplicationScope
    public OkHttp3Downloader provideOkHttpDownloader(OkHttpClient client) {
        return new OkHttp3Downloader(client);
    }
}