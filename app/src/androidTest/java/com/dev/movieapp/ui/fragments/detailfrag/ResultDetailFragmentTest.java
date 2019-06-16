package com.dev.movieapp.ui.fragments.detailfrag;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.dev.movieapp.R;
import com.dev.movieapp.models.Result;
import com.dev.movieapp.ui.activities.detail.ResultDetailActivity;
import com.dev.movieapp.utils.AppConstants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Boolean.FALSE;

@RunWith(AndroidJUnit4.class)
public class ResultDetailFragmentTest {

    public final Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    @Rule
    public final ActivityTestRule<ResultDetailActivity> detailActivityTestRule =
            new ActivityTestRule<ResultDetailActivity>(ResultDetailActivity.class,true,true){
                @Override
                protected Intent getActivityIntent() {

                    Intent intent = new Intent(targetContext, ResultDetailActivity.class);
                    Result result= new Result();
                    result.setAdult(FALSE);
                    result.setBackdropPath("/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg");
                    result.setId(1);
                    result.setOriginalTitle("Avengers");
                    result.setTitle("Avengers Marvel");
                    result.setVoteAverage(4.2);
                    result.setVoteCount(13);
                    result.setOverview("Movie Description");
                    result.setReleaseDate("2018-12-01");
                    Parcel parcel = Parcel.obtain();
                    result.writeToParcel(parcel,result.describeContents());
                    parcel.setDataPosition(0);
                    Result createdFromParcel = Result.CREATOR.createFromParcel(parcel);
                    intent.putExtra(AppConstants.RESULT_KEY, createdFromParcel);
                    return intent;
                }
            };

    @Test
    public void testUIElements(){
        onView(withId(R.id.title)).check(matches(withText("Avengers Marvel")));
        onView(withId(R.id.result_detail)).check(matches(withText("Movie Description")));
        onView(withId(R.id.votes)).check(matches(withText("4.2"+ " "+targetContext.getString(R.string.percentage))));
        onView(withId(R.id.releaseDate)).check(matches(withText(targetContext.getString(R.string.release)
                + " : " + ("01 Dec 2018")
        )));
        onView(withId(R.id.totalVotes)).check(matches(withText("13"+" "+targetContext.getString(R.string.votes))));
    }

}