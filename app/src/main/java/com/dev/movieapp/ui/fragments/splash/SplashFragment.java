package com.dev.movieapp.ui.fragments.splash;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.movieapp.R;
import com.dev.movieapp.ui.activities.splash.SplashActivity;
import com.dev.movieapp.ui.fragments.BaseFrag;
import com.dev.movieapp.utils.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment to handle Splash Screen
 * Initiates Presenter
 * Navigates to Landing Screen
 */
public class SplashFragment extends BaseFrag implements SplashView {

    private SplashPresenter mSplashPresenter;
    @Inject
    AppUtils mAppUtils;
    private Unbinder mUnbinder;
    @BindView(R.id.waitingLoader)
    ContentLoadingProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getInjectionComponent().inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mSplashPresenter = new SplashPresenter();
        mSplashPresenter.attach(this);
        mSplashPresenter.showWaiting();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.splash_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mProgressBar.setVisibility(View.VISIBLE);
        return rootView;
    }


    @Override
    public void checkNetwork() {
        mProgressBar.setVisibility(View.INVISIBLE);
        if (!mAppUtils.isNetworkConnected()) {
            showMessage(getString(R.string.network_error), R.string.retry, R.string.cancel);
        } else {
            navigateToLanding();
        }
    }

    @Override
    public void navigateToLanding() {
        mProgressBar.setVisibility(View.INVISIBLE);
        ((SplashActivity) getActivity()).navigateToLanding();
    }

    @Override
    public void handleNegativeAlertCallBack() {
        getActivity().finish();
    }

    @Override
    public void handlePositiveAlertCallBack() {
        mProgressBar.setVisibility(View.VISIBLE);
        checkNetwork();
    }

    @Override
    public void onDestroy() {
        mSplashPresenter.detach();
        mUnbinder = null;
        super.onDestroy();
    }
}
