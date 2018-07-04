package com.dev.movieapp.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for Context
 */
@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context context){
        mContext = context;
    }

    @Provides
    @Singleton
    Context getContext(){
        return mContext;
    }
}
