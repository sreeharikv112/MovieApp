package com.dev.movieapp.modules;

import android.util.Log;

import com.dev.movieapp.BuildConfig;
import com.dev.movieapp.networking.NetworkProcessor;
import com.dev.movieapp.networking.NetworkService;
import com.dev.movieapp.utils.AppUtils;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Module for Networking
 */

@Module
public class NetworkModule {

    private File mCacheFile;

    /**
     * Constructor for NetworkModule
     * @param cacheFile
     */
    public NetworkModule(File cacheFile) {
        this.mCacheFile = cacheFile;
    }

    @Provides
    @Singleton
    Retrofit provideCall(){

        //Sets up default cache with 10 MB
        Cache cache = null;
        int cacheSize = 10 * 1024 * 1024;
        try {
            cache = new Cache(mCacheFile, cacheSize);
        } catch (Exception e) {
            Log.e("TAG","Cache create error "+e.toString());
        }

        ConnectionSpec spec = new
                ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();
        //Sets @OkHttpClient with header
        //Request, Connection Time out , Read Time out etc
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(cache_interceptor)
                .cache(cache)
                .connectTimeout(AppUtils.TIME_OUT , TimeUnit.SECONDS)
                .readTimeout(AppUtils.TIME_OUT , TimeUnit.SECONDS)
                .connectionSpecs(Collections.singletonList(spec))
                .build();

            // RxJava2 service call support
            // Sets OkHttpClient
            // Sets Gson converter
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(providesGsonConverterFactory())
                .baseUrl(BuildConfig.BASE_URL).build();
            return retrofit;

    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkProcessor providesService(
            NetworkService networkService) {
        return new NetworkProcessor(networkService);
    }

    private static final Interceptor cache_interceptor = new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (new AppUtils().isNetworkConnected()) {
                int maxAge = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // keep 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }};

    private GsonConverterFactory providesGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return GsonConverterFactory.create(gsonBuilder.create());
    }
}
