package com.dev.movieapp.models;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Boolean.FALSE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ResultTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testIsResultParcelable() {
        Result result = new Result();
        result.setAdult(FALSE);
        result.setBackdropPath("/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg");
        result.setId(1);
        result.setOriginalTitle("Avengers");
        result.setTitle("Avengers Marvel");
        result.setVoteAverage(4.2);
        result.setVoteCount(13);
        result.setOverview("Movie Description");
        result.setReleaseDate("4-01-2018");
        Parcel parcel = Parcel.obtain();
        result.writeToParcel(parcel, result.describeContents());
        parcel.setDataPosition(0);

        Result createdFromParcel = Result.CREATOR.createFromParcel(parcel);

        assertThat(createdFromParcel.getOverview(), is("Movie Description"));
        assertThat(createdFromParcel.getVoteAverage(), is(4.2));
        assertThat(createdFromParcel.getVoteCount(), is(13));
        assertThat(createdFromParcel.getOriginalTitle(), is("Avengers"));
        assertThat(createdFromParcel.getTitle(), is("Avengers Marvel"));
        assertThat(createdFromParcel.getReleaseDate(), is("4-01-2018"));
        assertThat(createdFromParcel.getId(), is(1));
    }

    @After
    public void tearDown() {
    }
}