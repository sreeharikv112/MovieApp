package com.dev.movieapp.dipinject.components;


import com.dev.movieapp.dipinject.customscopes.ApplicationScope;
import com.dev.movieapp.dipinject.modules.ApplicationModule;
import com.dev.movieapp.dipinject.modules.NetworkModule;

import dagger.Component;

@ApplicationScope
@Component
        (
                modules = {ApplicationModule.class, NetworkModule.class}
        )
public interface ApplicationComponent {


    InjectionSubComponent newInjectionComponent();


}
