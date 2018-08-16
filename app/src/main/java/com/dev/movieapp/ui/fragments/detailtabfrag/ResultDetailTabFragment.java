package com.dev.movieapp.ui.fragments.detailtabfrag;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.movieapp.BuildConfig;
import com.dev.movieapp.R;
import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.fragments.BaseFrag;
import com.dev.movieapp.ui.fragments.detailfrag.ResultDetailFragment;
import com.dev.movieapp.utils.AppUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment class for Tablet Detailed view
 *
 */
public class ResultDetailTabFragment extends BaseFrag implements ResultDetailTabView{

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
    @BindView(R.id.img_movie)
    ImageView mImgMovie;
    Unbinder mUnbinder;
    ResultDetailTabPresenter mResultDetailTabPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ResultDetailTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        mResult = bundle.getParcelable(AppUtils.RESULT_KEY);
        mResultDetailTabPresenter=new ResultDetailTabPresenter(mResult);
        mResultDetailTabPresenter.attach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root_view= inflater.inflate(R.layout.fragment_result_detail_tab, container, false);
        mUnbinder=ButterKnife.bind(this, root_view);
        mResultDetailTabPresenter.refreshUI();
        return root_view;
    }

    /**
     * Repopulates UI with data elements
     * Sets up image as well
     * @param result
     */
    @Override
    public void populateUI(Result result) {
        mTitle.setText(result.getTitle());
        mVotes.setText(result.getVoteAverage() + " "+getString(R.string.percentage));
        mTotalVotes.setText(result.getVoteCount() + " "+getString(R.string.votes));
        mDescription.setText(result.getOverview());
        AppUtils appUtils=new AppUtils();
        mReleaseDate.setText(getString(R.string.release)+" : " + appUtils.getFormattedDate(result.getReleaseDate()));

        //Created URL for image population with Glide
        StringBuilder images = new StringBuilder();
        images.append(BuildConfig.IMG_BASE_URL+AppUtils.IMG_URL_EXTRA);
        images.append(result.getBackdropPath());
        loadImage(images.toString(),mImgMovie);
    }

    @Override
    public void onDestroy() {
        mResultDetailTabPresenter.detach();
        mUnbinder.unbind();
        super.onDestroy();
    }
}
