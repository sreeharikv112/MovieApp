package com.dev.movieapp.ui.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.movieapp.BuildConfig;
import com.dev.movieapp.R;
import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.activities.BaseActivity;
import com.dev.movieapp.ui.fragments.detailfrag.ResultDetailFragment;
import com.dev.movieapp.ui.activities.landing.ResultListActivity;
import com.dev.movieapp.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a single Result detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ResultListActivity}.
 */
public class ResultDetailActivity extends BaseActivity implements ResultDetailView {

    private Result mResult;

    @BindView(R.id.ivParallax)
    ImageView mImageParallax;

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    ResultDetailPresenter mResultDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mResult = getIntent().getExtras().getParcelable(AppUtils.RESULT_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        renderView();
        init();

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(AppUtils.RESULT_KEY,mResult);

            ResultDetailFragment fragment = new ResultDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.result_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public void renderView() {
        setContentView(R.layout.activity_result_detail);
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ResultDetailView detailView=this;
        // Initiate Presenter to manage data
        mResultDetailPresenter=new ResultDetailPresenter(mResult);
        mResultDetailPresenter.attach(detailView);
        mResultDetailPresenter.refreshUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            navigateUpTo(new Intent(this, ResultListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Refreshes UI with data
     * @param result
     */
    @Override
    public void populateUI(Result result) {
        if(!TextUtils.isEmpty(result.getBackdropPath())) {
            StringBuilder images = new StringBuilder();
            images.append(BuildConfig.IMG_BASE_URL + AppUtils.IMG_URL_EXTRA);
            images.append(result.getBackdropPath());
            loadImage(images.toString(),mImageParallax);
        }
    }

    @Override
    protected void onDestroy() {
        mResultDetailPresenter.detach();
        super.onDestroy();
    }
}
