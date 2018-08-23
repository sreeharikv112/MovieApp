package com.dev.movieapp.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;

import com.dev.movieapp.R;
import com.dev.movieapp.ui.activities.BaseActivity;
import com.dev.movieapp.ui.activities.landing.ResultListActivity;
import com.dev.movieapp.utils.AppUtils;

import javax.inject.Inject;

/**
 * Splash Activity
 * Validates internet connectivity
 * Pushes Landing Activity
 */
public class SplashActivity extends BaseActivity implements SplashView {

    SplashPresenter splashPresenter;
    @Inject
    AppUtils mAppUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getInjectionComponent().inject(this);
        super.onCreate(savedInstanceState);
        renderView();
        splashPresenter = new SplashPresenter();
        splashPresenter.attach(this);
        splashPresenter.delayedAction();
    }

    /**
     * Sets activity content
     */
    @Override
    public void renderView() {
        setContentView(R.layout.activity_splash);
    }

    /**
     * Initiates view
     */
    @Override
    public void init() {
    }

    /**
     * Validates network availability
     */
    @Override
    public void checkNetwork() {
        if (!mAppUtils.isNetworkConnected()) {
            showMessage(getString(R.string.network_error), R.string.retry, R.string.exit);
            return;
        } else {
            navigateToLanding();
        }
    }

    /**
     * Move to Landing screen
     */
    @Override
    public void navigateToLanding() {
        Intent intent = new Intent(this, ResultListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleNegativeAlertCallBack() {
        finish();
    }

    @Override
    public void handlePositiveAlertCallBack() {
        checkNetwork();
    }

    @Override
    protected void onDestroy() {
        splashPresenter.detach();
        super.onDestroy();
    }
}
