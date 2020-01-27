package com.odiousenterprises.theweather

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun requestValidLocation() {
        onView(withId(R.id.input_location_text)).perform(typeText("Ulaanbaatar"))
        onView(withId(R.id.input_location_button)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.weather_data_view)).check(matches(isDisplayed()))
    }

    @Test
    fun requestInvalidValidLocation() {
        onView(withId(R.id.input_location_text)).perform(typeText("Dagobah"))
        onView(withId(R.id.input_location_button)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.error_view)).check(matches(isDisplayed()))
    }
}
