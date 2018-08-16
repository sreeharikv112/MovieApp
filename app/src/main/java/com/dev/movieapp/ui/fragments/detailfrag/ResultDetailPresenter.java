package com.dev.movieapp.ui.fragments.detailfrag;

import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.fragments.detailfrag.*;
import com.dev.movieapp.ui.uiinterfaces.BasePresenter;

/**
 * Presenter class for Detail Fragment
 */

public class ResultDetailPresenter implements BasePresenter<ResultDetailView >{

    private Result result;
    private ResultDetailView resultDetailView;

    /**
     * Constructor for Presenter
     * @param result
     */
    public ResultDetailPresenter(Result result){
        this.result = result;
    }

    /**
     * Refreshes UI with data
     */
    public void refreshUI(){
        resultDetailView.populateUI(result);
    }

    @Override
    public void attach(ResultDetailView view) {
        this.resultDetailView = view;
    }

    @Override
    public void detach() {
        resultDetailView=null;
    }
}
