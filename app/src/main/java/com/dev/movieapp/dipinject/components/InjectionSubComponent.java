package com.dev.movieapp.dipinject.components;

import com.dev.movieapp.ui.activities.landing.ResultListActivity;
import com.dev.movieapp.ui.activities.splash.SplashActivity;
import com.dev.movieapp.ui.fragments.detailfrag.ResultDetailFragment;
import com.dev.movieapp.ui.fragments.detailtabfrag.ResultDetailTabFragment;
import com.dev.movieapp.ui.fragments.splash.SplashFragment;
import com.dev.movieapp.ui.fragments.splash.SplashPresenter;

import dagger.Subcomponent;

@Subcomponent
public interface InjectionSubComponent {

    void inject(ResultListActivity activity);

    void inject(ResultDetailFragment fragment);

    /*void inject(SplashActivity activity);*/

    void inject(ResultDetailTabFragment fragment);

    void inject(SplashFragment fragment);

}
