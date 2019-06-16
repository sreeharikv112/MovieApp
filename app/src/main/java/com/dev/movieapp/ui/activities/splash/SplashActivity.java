package com.dev.movieapp.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

import com.dev.movieapp.R;
import com.dev.movieapp.ui.activities.BaseActivity;
import com.dev.movieapp.ui.activities.landing.ResultListActivity;
import com.dev.movieapp.ui.fragments.splash.SplashFragment;
import com.dev.movieapp.utils.AppConstants;

/**
 * Splash Activity
 * Validates internet connectivity
 * Pushes Landing Activity
 */
public class SplashActivity extends BaseActivity  {

    private SplashFragment mSplashFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView();

        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mSplashFragment=(SplashFragment) fm.getFragment(savedInstanceState, AppConstants.SPLASH_FRAGMENT );
        }else{
            mSplashFragment = new SplashFragment();
            fm.beginTransaction().add(R.id.splash_content_frame,mSplashFragment,AppConstants.SPLASH_FRAGMENT ).commit();
        }
    }

    /**
     * Sets activity content
     */
    @Override
    public void renderView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void init() {
    }

    /**
     * Navigate to Landing Screen
     */
    public void navigateToLanding() {
        Intent intent = new Intent(this, ResultListActivity.class);
        startActivity(intent);
        finish();
    }
}
