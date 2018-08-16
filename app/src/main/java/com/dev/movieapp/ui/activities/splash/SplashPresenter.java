package com.dev.movieapp.ui.activities.splash;

import android.os.Handler;

import com.dev.movieapp.ui.uiinterfaces.BasePresenter;

/**
 * Presenter class for Splash View
 */

public class SplashPresenter implements BasePresenter<SplashView>{

    // Splash screen timer
    private final int SPLASH_TIME_OUT = 1000;
    protected SplashView mSplashView;

    /**
     * Constructor for Presenter
     */
    public SplashPresenter(){
    }

    public void delayedAction(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                postDelayAction();
            }
        }, SPLASH_TIME_OUT);
    }


    /**
     * Step after splash timeout
     */
    void postDelayAction(){
        mSplashView.checkNetwork();
    }

    @Override
    public void attach(SplashView view) {
        mSplashView = view;
    }

    @Override
    public void detach() {
        mSplashView=null;
    }
}
