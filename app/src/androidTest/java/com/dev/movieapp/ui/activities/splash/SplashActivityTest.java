package com.dev.movieapp.ui.activities.splash;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dev.movieapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public final ActivityTestRule<SplashActivity> splashActivityActivityTestRule=
            new ActivityTestRule<>(SplashActivity.class,true, true);

    @Before
    public void init(){

    }

    /*@Test
    public void testViewElements() {

        onView(withId(R.id.splashImageId)).check(matches(isDisplayed()));
    }*/
}