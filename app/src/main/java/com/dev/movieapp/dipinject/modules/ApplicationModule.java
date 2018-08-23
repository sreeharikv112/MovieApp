package com.dev.movieapp.dipinject.modules;

import android.app.Application;

import com.dev.movieapp.dipinject.customscopes.ApplicationScope;
import com.dev.movieapp.utils.AppLogger;
import com.dev.movieapp.utils.AppUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    Application application() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    AppLogger logger() {
        return new AppLogger();
    }

    @Provides
    @ApplicationScope
    AppUtils getUtils() {
        return new AppUtils(mApplication);
    }
}
