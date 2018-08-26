package com.dev.movieapp.ui.fragments.detailfrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.movieapp.R;
import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.activities.detail.ResultDetailActivity;
import com.dev.movieapp.ui.activities.landing.ResultListActivity;
import com.dev.movieapp.ui.fragments.BaseFrag;
import com.dev.movieapp.utils.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.dev.movieapp.utils.AppConstants.RESULT_KEY;

/**
 * A fragment representing a single Result detail screen.
 * This fragment is either contained in a {@link ResultListActivity}
 * in two-pane mode (on tablets) or a {@link ResultDetailActivity}
 * on handsets.
 */
public class ResultDetailFragment extends BaseFrag implements ResultDetailView {


    private Unbinder mUnbinder;
    private Result mResult;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.votes)
    TextView mVotes;
    @BindView(R.id.releaseDate)
    TextView mReleaseDate;
    @BindView(R.id.totalVotes)
    TextView mTotalVotes;
    @BindView(R.id.result_detail)
    TextView mDescription;
    @BindView(R.id.toolbar_layout)
    @Nullable
    CollapsingToolbarLayout mAppBarLayout;
    ResultDetailPresenter mResultDetailPresenter;
    @Inject
    AppUtils mAppUtils;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ResultDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getInjectionComponent().inject(this);
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        mResult = bundle.getParcelable(RESULT_KEY);
        //Initiates Presenter object
        mResultDetailPresenter = new ResultDetailPresenter(mResult);
        mResultDetailPresenter.attach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.result_detail, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mResultDetailPresenter.refreshUI();
        return rootView;
    }

    /**
     * Repopulates UI
     *
     * @param result
     */
    @Override
    public void populateUI(Result result) {
        mTitle.setText(result.getTitle());
        mVotes.setText(result.getVoteAverage() + " " + getString(R.string.percentage));
        mTotalVotes.setText(result.getVoteCount() + " " + getString(R.string.votes));
        mDescription.setText(result.getOverview());
        mReleaseDate.setText(getString(R.string.release) + " : " + mAppUtils.getFormattedDate(result.getReleaseDate()));
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        mResultDetailPresenter.detach();
        super.onDestroy();
    }
}
