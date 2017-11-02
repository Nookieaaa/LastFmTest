package com.nookdev.lastfmtest.base.di.modules;

import android.content.Context;

import com.nookdev.lastfmtest.R;
import com.nookdev.lastfmtest.base.di.qualifiers.ApiKeyQualifier;
import com.nookdev.lastfmtest.base.di.scopes.ApplicationScope;
import com.nookdev.lastfmtest.util.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = {ContextModule.class, SharedPreferencesModule.class})
public class NetworkModule {

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @ApplicationScope
    public Interceptor provideBaseInterceptor(@ApiKeyQualifier final String apiKey) {
        return chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalUrl = originalRequest.url();
            HttpUrl newUrl = originalUrl.newBuilder()
                    .addQueryParameter(Constants.API_KEY_ALIAS, apiKey)
                    .addQueryParameter(Constants.API_OUTPUT_FORMAT_ALIAS, Constants.API_OUTPUT_FORMAT)
                    .build();
            Request newRequest = originalRequest.newBuilder()
                    .url(newUrl).build();
            return chain.proceed(newRequest);
        };
    }

    @Provides
    @ApplicationScope
    public File provideCacheFile(Context context) {
        return new File(context.getCacheDir(), Constants.HTTP_CACHE_FILE);
    }

    @Provides
    @ApplicationScope
    public Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, Constants.HTTP_CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                            Interceptor baseInterceptor,
                                            Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(baseInterceptor)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @ApiKeyQualifier
    @ApplicationScope
    public String provideApiKey(Context context) {
        return context.getString(R.string.lastfm_api_key);
    }
}