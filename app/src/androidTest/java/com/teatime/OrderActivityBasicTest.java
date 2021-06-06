
package com.teatime;


import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * This test demos a user clicking the decrement button and verifying that it properly decrease
 * the quantity the total cost.
 */

@RunWith(AndroidJUnit4.class)
public class OrderActivityBasicTest {

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */

    @Rule
    public ActivityTestRule<OrderActivity> mActivityTestRule =
            new ActivityTestRule<>(OrderActivity.class);

    @BeforeClass
    public static void beforeClass() {
        Log.v("Testing","Before Class");
    }

    @Before
    public void before() {
        Log.v("Testing","Before");
    }

    @Test
    public void clickDecrementButton_ChangesQuantityAndCost() {
        Log.v("Testing","Click Decrement");
        // Check the initial quantity variable is zero
        onView((withId(R.id.quantity_text_view))).check(matches(withText(R.string.initial_quantity_value)));

        // Click on decrement button
        onView((withId(R.id.decrement_button)))
                .perform(click());

        // Verify that the decrement button decreases the quantity by 1 untill to 0
        onView(withId(R.id.quantity_text_view))
                .check(matches(withText("0")));

        // Verify that the decrement button also decreases the total cost to $0.00
        onView(withId(R.id.cost_text_view)).check(matches(withText("$0.00")));

    }

    @Test
    public void clickIncrementButton_ChangesQuantityAndCost() {
        Log.v("Testing","Click Increment");
        // Check the initial quantity variable is zero
        onView((withId(R.id.quantity_text_view))).check(matches(withText(R.string.initial_quantity_value)));


        // Click on increment button
        onView((withId(R.id.increment_button)))
                .perform(click());

        // Verify that the increment button increases the quantity by 1 untill soon
        onView(withId(R.id.quantity_text_view))
                .check(matches(withText("1")));

        // Verify that the increment button also increases the total cost to $5.00 by default in th size spinner
        onView(withId(R.id.cost_text_view)).check(matches(withText("$5.00")));

    }

    @After
    public void after() {
        Log.v("Testing","After");
    }

    @AfterClass
    public static void afterClass() {
        Log.v("Testing","After Class");
    }
}