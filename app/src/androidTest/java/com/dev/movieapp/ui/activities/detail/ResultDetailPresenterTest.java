package com.dev.movieapp.ui.activities.detail;

import android.support.test.runner.AndroidJUnit4;

import com.dev.movieapp.models.Result;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static java.lang.Boolean.FALSE;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class ResultDetailPresenterTest {

    Result result;
    @Mock
    ResultDetailView resultDetailView;


    @Before
    public void initiate() {
        MockitoAnnotations.initMocks(this);
        result = new Result();
        result.setAdult(FALSE);
        result.setBackdropPath("/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg");
        result.setId(1);
    }

    @Test
    public void testAttach() {

        ResultDetailPresenter resultDetailPresenter = new ResultDetailPresenter(result);

        assertNull(resultDetailPresenter.mResultDetailView);

        resultDetailPresenter.attach(resultDetailView);

        assertNotNull(resultDetailPresenter.mResultDetailView);
    }


    @Test
    public void testRefreshUI() {

        ResultDetailPresenter resultDetailPresenter = new ResultDetailPresenter(result);

        resultDetailPresenter.attach(resultDetailView);

        resultDetailPresenter.refreshUI();

        verify(resultDetailView, atLeastOnce()).populateUI(result);
    }
}