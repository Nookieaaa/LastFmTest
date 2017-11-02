package com.nookdev.lastfmtest.base.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nookdev.lastfmtest.R;
import com.nookdev.lastfmtest.api.LastFmApi;
import com.nookdev.lastfmtest.base.di.qualifiers.ApiEndpointQualifier;
import com.nookdev.lastfmtest.base.di.scopes.ApplicationScope;
import com.nookdev.lastfmtest.models.Artist;
import com.nookdev.lastfmtest.models.ArtistDeserializer;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @ApplicationScope
    public LastFmApi provideLetLiftApiService(Retrofit retrofit) {
        return retrofit.create(LastFmApi.class);
    }

    @Provides
    @ApplicationScope
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Artist.class, new ArtistDeserializer())
                .create();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson, @ApiEndpointQualifier String endpoint) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    @ApiEndpointQualifier
    public String provideApiEndpoint(Context context) {
        return context.getString(R.string.api_endpoint);
    }

}
