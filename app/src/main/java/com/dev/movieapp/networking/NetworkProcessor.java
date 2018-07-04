package com.dev.movieapp.networking;

import android.text.TextUtils;
import android.util.Log;

import com.dev.movieapp.models.PopularMovies;
import com.dev.movieapp.utils.AppUtils;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Handles network call for getting response from REST API
 */

public class NetworkProcessor {

    private final NetworkService mNetworkService;

    /**
     *
     * @param networkService
     */
    public NetworkProcessor(NetworkService networkService){
        mNetworkService=networkService;
    }

    /**
     * Initiates service call using reactivex
     * @param callback
     * @param api_key
     * @param lang
     * @param page
     */
    public void getPopularList(final GetPopularMovieCallBack callback,String api_key,String lang,String page){

        Single singleResponse = mNetworkService.getPopularMovies(api_key,lang,page);
        singleResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PopularMovies>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onSuccess(PopularMovies response) {
                        //Initiate success callback
                        callback.onSuccess(response);
                    }
                    @Override
                    public void onError(Throwable e) {
                        //Handles error response
                        Log.e(NetworkProcessor.class.getCanonicalName(),"Error = "+e.toString());
                        NetError error = new NetError();
                        if(!TextUtils.isEmpty(e.toString())){
                            error.errorMsg = e.toString();
                        }else {
                            error.errorMsg = AppUtils.API_ERROR_PROCESS;
                        }
                        callback.onError(error);
                    }
                });
    }


    /**
     * Call backs for success and error scenarios
     */
    public interface GetPopularMovieCallBack{
        void onSuccess(PopularMovies movieList);
        void onError(NetError networkError);
    }
}
