package com.dev.movieapp.networking;


import com.dev.movieapp.models.PopularMovies;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service end point configuration
 */

public interface NetworkService {

    @GET("3/movie/popular")
    Single<PopularMovies> getPopularMovies(@Query("api_key") String api_key, @Query("language") String language,
                                           @Query("page") String page);

}
