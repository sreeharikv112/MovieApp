package com.dev.movieapp.ui.fragments.detailtabfrag;

import com.dev.movieapp.models.Result;

/**
 * Presenter class for Detail Fragment Tablet specific
 */

public class ResultDetailTabPresenter {
    private Result result;
    private ResultDetailTabView resultDetailTabView;

    /**
     * Constructor for Presenter
     * @param result
     * @param resultDetailTabView
     */
    public ResultDetailTabPresenter(Result result,ResultDetailTabView resultDetailTabView){
        this.result = result;
        this.resultDetailTabView = resultDetailTabView;
    }
    /**
     * Refreshes UI with data
     */
    public void refreshUI(){
        resultDetailTabView.populateUI(result);
    }
}
