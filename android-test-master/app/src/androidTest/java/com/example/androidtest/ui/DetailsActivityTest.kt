package com.example.androidtest.ui

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.androidtest.R
import com.example.androidtest.models.User
import com.example.androidtest.utils.DATA_KEY
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {

    @get: Rule
    var mActivityRule: ActivityTestRule<DetailsActivity> = object : ActivityTestRule<DetailsActivity>(DetailsActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val resultData = Intent()
            resultData.putExtra(DATA_KEY, User(1231231, "jaggrat singh", "", 1111, 123434511, "London", false, false))
            return resultData
        }
    }

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }


    @Test
    fun testDisplayName() {
        Espresso.onView(withText("Display Name: jaggrat singh")).check(matches(isDisplayed()))
    }

    @Test
    fun testDisplayReputation() {
        Espresso.onView(withText("Reputation: 1111")).check(matches(isDisplayed()))
    }

    @Test
    fun testDisplayAccountId() {
        Espresso.onView(withText("Account Id: 1231231")).check(matches(isDisplayed()))
    }

    @Test
    fun testDisplayLocation() {
        Espresso.onView(withText("Location: London")).check(matches(isDisplayed()))
    }

    @Test
    fun testDisplayStatus() {
        Espresso.onView(withText("Status: Please choose an option to follow or block")).check(matches(isDisplayed()))
    }

    @Test
    fun testFollowClick() {
        Espresso.onView(withId(R.id.bt_follow)).perform(click())
        Espresso.onView(withText("Status: Following")).check(matches(isDisplayed()))
    }

    @Test
    fun testBlockClick() {
        Espresso.onView(withId(R.id.bt_block)).perform(click())
        Espresso.onView(withText("Status: Block")).check(matches(isDisplayed()))
    }
}