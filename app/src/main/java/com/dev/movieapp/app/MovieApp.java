package com.dev.movieapp.app;

import android.app.Application;
import androidx.lifecycle.ProcessLifecycleOwner;
import android.content.res.Configuration;

import com.dev.movieapp.dipinject.components.ApplicationComponent;
import com.dev.movieapp.dipinject.components.DaggerApplicationComponent;
import com.dev.movieapp.dipinject.modules.ApplicationModule;
import com.dev.movieapp.dipinject.modules.NetworkModule;

import java.io.File;

public class MovieApp extends Application {

    private ApplicationComponent mApplicationComponent;
    private File mCacheFile;
    private boolean isAppActive;

    @Override
    public void onCreate() {
        super.onCreate();
        mCacheFile = new File(getCacheDir(), "popular_movie_response");

        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppEventsListener(this));
    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule(mCacheFile, this))
                    .build();
        }
        return mApplicationComponent;
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

    public boolean isAppActive() {
        return isAppActive;
    }

    public void setAppActive(boolean appActive) {
        isAppActive = appActive;
    }

}
