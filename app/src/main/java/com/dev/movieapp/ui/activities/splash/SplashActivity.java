package com.dev.movieapp.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;

import com.dev.movieapp.R;
import com.dev.movieapp.ui.activities.landing.ResultListActivity;
import com.dev.movieapp.ui.activities.BaseActivity;
import com.dev.movieapp.utils.AppUtils;

/**
 * Splash Activity
 * Validates internet connectivity
 * Pushes Landing Activity
 */
public class SplashActivity extends BaseActivity implements SplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView();
        SplashPresenter splashPresenter=new SplashPresenter(this);
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
        if(!new AppUtils().isNetworkConnected()){
            showMessage(getString(R.string.network_error),R.string.retry,R.string.exit);
            return;
        }else{
            navigateToLanding();
        }
    }

    /**
     * Move to Landing screen
     */
    @Override
    public void navigateToLanding() {
        Intent intent= new Intent(this,ResultListActivity.class);
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
}
