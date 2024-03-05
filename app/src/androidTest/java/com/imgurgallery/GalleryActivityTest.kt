package com.imgurgallery

import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imgurgallery.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI test for HomeActivity.
 */

@RunWith(AndroidJUnit4::class)
class GalleryActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    /**
     * Test when gallery is being fetched,
     */
    @Test
    fun is_loading() {
        Espresso
            .onView(
                ViewMatchers
                    .withId(R.id.loader)
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
    }

    /**
     * Test when gallery is fetched,
     */
    @Test
    fun is_loaded() {
        //Wait list to be loaded.
        SystemClock.sleep(7000)
        Espresso
            .onView(
                ViewMatchers
                    .withId(R.id.gallery_list)
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
    }

}