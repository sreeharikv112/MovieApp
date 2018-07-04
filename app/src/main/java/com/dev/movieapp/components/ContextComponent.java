package com.dev.movieapp.components;

import com.dev.movieapp.modules.ContextModule;
import com.dev.movieapp.utils.AppUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component to inject Context into interested parties
 */
@Singleton
@Component (modules = ContextModule.class)
public interface ContextComponent {

    void inject(AppUtils appUtils);
}
