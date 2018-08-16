package com.dev.movieapp.ui.uiinterfaces;

import android.view.View;

public interface BasePresenter<T> {

    public void attach(T view);

    public void detach();
}
