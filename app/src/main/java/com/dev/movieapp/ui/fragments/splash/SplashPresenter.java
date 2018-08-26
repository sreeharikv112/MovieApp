package com.dev.movieapp.ui.fragments.splash;

import android.os.Handler;

import com.dev.movieapp.ui.uiinterfaces.BasePresenter;

/**
 * Presenter for Splash
 * Shows waiting
 */
public class SplashPresenter implements BasePresenter<SplashView> {

    protected SplashView mSplashView;
    private Handler mHandler;
    private final int SPLASH_TIME_OUT = 5000;

    public SplashPresenter() {
    }

    public void showWaiting() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                postDelayedAction();
            }
        }, SPLASH_TIME_OUT);
    }

    public void postDelayedAction() {
        mSplashView.checkNetwork();
    }

    @Override
    public void attach(SplashView splashView) {
        mSplashView = splashView;
    }

    @Override
    public void detach() {
        mSplashView = null;
    }
}
