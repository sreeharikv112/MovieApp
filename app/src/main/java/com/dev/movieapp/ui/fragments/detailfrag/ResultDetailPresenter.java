package com.dev.movieapp.ui.fragments.detailfrag;

import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.fragments.detailfrag.*;

/**
 * Presenter class for Detail Fragment
 */

public class ResultDetailPresenter {

    private Result result;
    private ResultDetailView resultDetailView;

    /**
     * Constructor for Presenter
     * @param result
     * @param resultDetailView
     */
    public ResultDetailPresenter(Result result, ResultDetailView resultDetailView){
        this.result = result;
        this.resultDetailView = resultDetailView;
    }

    /**
     * Refreshes UI with data
     */
    public void refreshUI(){
        resultDetailView.populateUI(result);
    }
}
