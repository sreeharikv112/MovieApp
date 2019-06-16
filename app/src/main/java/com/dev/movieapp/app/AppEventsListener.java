package com.dev.movieapp.app;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class AppEventsListener implements LifecycleObserver {

    private MovieApp mApp;

    public AppEventsListener(MovieApp app){
        mApp=app;
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void gotCreated(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void gotStarted(){

        mApp.setAppActive(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void gotStopped(){

        mApp.setAppActive(false);
    }
}
