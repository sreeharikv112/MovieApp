package com.dev.movieapp.ui.activities.landing;


import com.dev.movieapp.models.ModelBase;
import com.dev.movieapp.models.PopularMovies;
import com.dev.movieapp.models.Result;
import com.dev.movieapp.networking.NetError;
import com.dev.movieapp.networking.NetworkProcessor;
import com.dev.movieapp.ui.uiinterfaces.BasePresenter;

/**
 * Presenter class for Main View with List items
 */

public class ResultListPresenter implements NetworkProcessor.NetworkResponseCallBack , BasePresenter<ResultListActivityView>{

    private final NetworkProcessor mNetworkProcessor;
    private ResultListActivityView mView;

    /**
     * Constructor for Presenter
     * @param networkProcessor
     */
    public ResultListPresenter(NetworkProcessor networkProcessor){
        this.mNetworkProcessor = networkProcessor;
    }

    /**
     * Manipulates UI through
     * Initiates network call
     * @param api_key
     * @param lang
     * @param page
     */
    public void fetchMovieList(String api_key,String lang,int page) {

        mNetworkProcessor.getPopularList(this,api_key,lang,String.valueOf(page));
    }

    /**
     * Handles click on specific element
     * @param result
     */
    public void itemSelected(Result result){
        mView.pushDetailView(result);
    }

    /**
     * Intimates UI changes through {@link ResultListActivityView} on positive flow
     * @param movieList
     */
    @Override
    public void onSuccess(ModelBase movieList) {
        PopularMovies popularMovies = (PopularMovies)movieList;
        mView.getMovieList(popularMovies);
    }

    /**
     * Intimates UI changes through {@link ResultListActivityView} on negative flow
     * @param networkError
     */
    @Override
    public void onError(NetError networkError) {
        mView.onFailure(networkError.errorMsg);
    }

    @Override
    public void attach(ResultListActivityView view) {
        mView=view;
    }

    @Override
    public void detach() {
        mView=null;
    }
}
