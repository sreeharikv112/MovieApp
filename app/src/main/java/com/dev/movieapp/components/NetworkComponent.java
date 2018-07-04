package com.dev.movieapp.components;


import com.dev.movieapp.ui.activities.landing.ResultListActivity;
import com.dev.movieapp.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component for injecting NetworkModule into interested parties
 */

@Singleton
@Component (modules = NetworkModule.class)
public interface NetworkComponent {

    void inject(ResultListActivity mainActivity);
}
