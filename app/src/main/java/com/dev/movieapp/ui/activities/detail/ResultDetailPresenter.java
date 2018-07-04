package com.dev.movieapp.ui.activities.detail;

import com.dev.movieapp.models.Result;

/**
 * Class representing Presenter for Detail view
 */

public class ResultDetailPresenter {

    Result mResult;
    ResultDetailView mResultDetailView;

    /**
     * Constructor for Presenter
     * @param result
     * @param resultDetailView
     */
    public ResultDetailPresenter(Result result,ResultDetailView resultDetailView){
        mResult = result;
        mResultDetailView = resultDetailView;

    }

    /**
     * Refreshes UI with data
     */
    public void refreshUI(){
        mResultDetailView.populateUI(mResult);
    }
}
