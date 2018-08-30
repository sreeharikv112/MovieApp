package com.dev.movieapp.ui.fragments.splash;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dev.movieapp.ui.activities.detail.ResultDetailActivity;
import com.dev.movieapp.ui.activities.splash.SplashActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class SplashPresenterTest {

    @Mock
    SplashView mSplashView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAttach(){
        SplashPresenter splashPresenter=new SplashPresenter();
        assertNull(splashPresenter.mSplashView);
        splashPresenter.attach(mSplashView);
        assertNotNull(splashPresenter.mSplashView);
    }

    @Test
    public void testPostDelayedAction() {
        SplashPresenter splashPresenter=new SplashPresenter();
        splashPresenter.attach(mSplashView);
        splashPresenter.postDelayedAction();
        verify(mSplashView,atLeastOnce()).checkNetwork();
    }

    @Test
    public void testDetach() {
        SplashPresenter splashPresenter=new SplashPresenter();
        splashPresenter.attach(mSplashView);
        assertNotNull(splashPresenter.mSplashView);
        splashPresenter.detach();
        assertNull(splashPresenter.mSplashView);
    }


}