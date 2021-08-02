package test.app.android_school

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import test.app.android_school.mvvm.MainActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class EspressoTestFirst {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun changeText_sameActivity() {
        onView(withId(R.id.floating_button)).perform(click())

        onView(withId(R.id.task_edit_text)).perform(typeText("Test text Expresso"))
        onView(withId(R.id.task_edit_text)).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.task_edit_text)).check(matches(withText("Test text Expresso")))

        onView(withId(R.id.spinner)).perform(click())
        onData(anything()).atPosition(1).perform(click())

        onView(withId(R.id.switch_view)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.ok_calendare_button))

        onView(withId(R.id.ok_button)).perform(click())

    }

}