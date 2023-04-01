package com.example.qrscanner

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun generateNavigationClick_generatePageShows() {
        onView(withId(R.id.generate_page))
            .perform(click())

        onView(withId(R.id.generateButton)).check(matches(isDisplayed()))
    }

    @Test
    fun generateNavigationClick_noQrCodeImage() {
        onView(withId(R.id.generate_page))
            .perform(click())

        onView(withId(R.id.qrCodeImage))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun generateNavigationClick_enterDataForEncode_clickGenerate_showQrCode() {
        onView(withId(R.id.generate_page))
            .perform(click())

        onView(withId(R.id.contentInput))
            .perform(typeText("some text"))

        onView(withId(R.id.generateButton))
            .perform(click())

        onView(withId(R.id.qrCodeImage))
            .check(matches(isDisplayed()))
    }

    @Test
    fun generateNavigationClick_clickGenerate_showError() {
        onView(withId(R.id.generate_page))
            .perform(click())

        onView(withId(R.id.generateButton))
            .perform(click())

        onView(withText("Error"))
            .check(matches(isDisplayed()))
    }
}