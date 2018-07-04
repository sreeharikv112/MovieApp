package com.dev.movieapp.ui.activities.landing;


import com.dev.movieapp.models.PopularMovies;
import com.dev.movieapp.models.Result;

/**
 * View interface for Main List View
 */

public interface ResultListActivityView {

    void onFailure(String appErrorMessage);

    void getMovieList(PopularMovies movieList);

    void pushDetailView(Result result);
}
