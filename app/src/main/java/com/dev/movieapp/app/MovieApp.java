package com.dev.movieapp.app;

import android.app.Application;
import android.content.res.Configuration;

import com.dev.movieapp.components.ContextComponent;
import com.dev.movieapp.components.DaggerContextComponent;
import com.dev.movieapp.components.DaggerNetworkComponent;
import com.dev.movieapp.components.NetworkComponent;
import com.dev.movieapp.modules.ContextModule;
import com.dev.movieapp.modules.NetworkModule;

import java.io.File;

public class MovieApp extends Application {

    private static MovieApp movieAppInstance;
    NetworkComponent mNetworkComponent;
    private ContextComponent mContextComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        movieAppInstance=this;

        File cacheFile = new File(getCacheDir(), "popularmovieresponses");
        mNetworkComponent = createNetworkComponent(cacheFile);
        mContextComponent =  createContextComponent();
    }

    public static MovieApp getInstance(){
        return movieAppInstance;
    }

    private NetworkComponent createNetworkComponent(File cacheFile) {
        return DaggerNetworkComponent.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private ContextComponent createContextComponent() {

        return DaggerContextComponent.builder().contextModule(new ContextModule(this)).build();
    }

    public ContextComponent getContextComponent(){
        return mContextComponent;
    }
}
