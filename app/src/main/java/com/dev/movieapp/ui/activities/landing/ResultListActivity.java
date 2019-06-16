package com.dev.movieapp.ui.activities.landing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.movieapp.BuildConfig;
import com.dev.movieapp.R;
import com.dev.movieapp.models.PopularMovies;
import com.dev.movieapp.models.Result;
import com.dev.movieapp.networking.NetworkProcessor;
import com.dev.movieapp.ui.activities.BaseActivity;
import com.dev.movieapp.ui.activities.detail.ResultDetailActivity;
import com.dev.movieapp.ui.fragments.detailtabfrag.ResultDetailTabFragment;
import com.dev.movieapp.utils.AppUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dev.movieapp.utils.AppConstants.RESULT_KEY;

/**
 * An activity representing a list of Results. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ResultDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ResultListActivity extends BaseActivity implements ResultListActivityView {

    @Inject
    public NetworkProcessor mNetworkProcessor;
    @Inject
    public AppUtils mAppUtils;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ResultListPresenter mPresenter;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getInjectionComponent().inject(this);
        super.onCreate(savedInstanceState);
        renderView();
        init();
        mPresenter = new ResultListPresenter(mNetworkProcessor);
        mPresenter.attach(this);

        if (findViewById(R.id.result_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        requestMovieList();
    }

    @Override
    public void renderView() {
        setContentView(R.layout.activity_result_list);
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showMessage(appErrorMessage, R.string.ok, R.string.cancel);
    }

    private void requestMovieList() {
        if (!mAppUtils.isNetworkConnected()) {
            showMessage(getString(R.string.network_error), R.string.retry, R.string.cancel);
            return;
        }
        mPresenter.fetchMovieList(BuildConfig.API_KEY, BuildConfig.API_LANG, mPage);
    }

    @Override
    public void getMovieList(PopularMovies movieList) {
        if (movieList == null || movieList.getResults().size() == 0) {
            Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();
        } else {
            View recyclerView = findViewById(R.id.result_list);
            assert recyclerView != null;
            setupRecyclerView((RecyclerView) recyclerView, movieList.getResults());
        }
    }

    /**
     * Populates RecyclerView with retrieved data
     *
     * @param recyclerView
     * @param data
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Result> data) {
        recyclerView.setAdapter(new MovieListAdapter(data, new MovieListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Result item) {
                mPresenter.itemSelected(item);
            }
        }));
    }

    /**
     * Check for two pane mode
     * Pushes Activity if not
     * Manages Fragment for two pane
     *
     * @param result
     */
    @Override
    public void pushDetailView(Result result) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(RESULT_KEY, result);
            ResultDetailTabFragment fragment = new ResultDetailTabFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.result_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, ResultDetailActivity.class);
            intent.putExtra(RESULT_KEY, result);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
