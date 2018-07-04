package com.dev.movieapp.ui.activities.splash;

import android.os.Handler;

/**
 * Presenter class for Splash View
 */

public class SplashPresenter {

    // Splash screen timer
    private final int SPLASH_TIME_OUT = 1000;
    private SplashView mSplashView;

    /**
     * Constructor for Presenter
     * @param splashView
     */
    public SplashPresenter(SplashView splashView){
        mSplashView = splashView;
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
}
