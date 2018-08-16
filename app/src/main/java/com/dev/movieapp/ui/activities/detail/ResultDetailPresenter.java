package com.dev.movieapp.ui.activities.detail;

import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.uiinterfaces.BasePresenter;

/**
 * Class representing Presenter for Detail view
 */

public class ResultDetailPresenter implements BasePresenter<ResultDetailView> {

    Result mResult;
    ResultDetailView mResultDetailView;

    public ResultDetailView getResultDetailView() {
        return mResultDetailView;
    }

    /**
     * Constructor for Presenter
     * @param result
     */
    public ResultDetailPresenter(Result result){
        mResult = result;
    }

    public void attach(ResultDetailView resultDetailView){
        mResultDetailView = resultDetailView;
    }

    /**
     * Refreshes UI with data
     */
    public void refreshUI(){

        getResultDetailView().populateUI(mResult);
    }

    public void detach(){
        mResultDetailView=null;
    }
}
