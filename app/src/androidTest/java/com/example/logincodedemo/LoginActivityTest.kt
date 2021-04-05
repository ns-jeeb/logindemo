package com.example.logincodedemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.logincodedemo.ui.login.LoginActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    private lateinit var userStringToBetyped: String
    private lateinit var passwordStringToBetyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity>
            = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun initValidUserNameString() {
        // Specify a valid string.
        userStringToBetyped = "Username"
    }

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.username))
            .perform(typeText(userStringToBetyped), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.username))
            .check(matches(withText(userStringToBetyped)))

    }

    @Before
    fun initValidPassString() {
        // Specify a valid string.
        passwordStringToBetyped = "Password"
    }

    @Test
    fun changeText_login_activity() {
        // Type text and then press the button.
        onView(withId(R.id.password))
            .perform(typeText(passwordStringToBetyped), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.password))
            .check(matches(withText(passwordStringToBetyped)))
    }


}