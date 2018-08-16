package com.dev.movieapp.ui.fragments.detailtabfrag;

import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.uiinterfaces.BasePresenter;

/**
 * Presenter class for Detail Fragment Tablet specific
 */

public class ResultDetailTabPresenter implements BasePresenter<ResultDetailTabView> {
    private Result result;
    private ResultDetailTabView resultDetailTabView;

    /**
     * Constructor for Presenter
     * @param result
     */
    public ResultDetailTabPresenter(Result result){
        this.result = result;

    }

    public void attach(ResultDetailTabView resultDetailTabView){
        this.resultDetailTabView = resultDetailTabView;
    }
    /**
     * Refreshes UI with data
     */
    public void refreshUI(){
        resultDetailTabView.populateUI(result);
    }

    public void detach(){
        this.resultDetailTabView=null;
    }
}
