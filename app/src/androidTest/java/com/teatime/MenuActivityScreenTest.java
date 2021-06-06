
package com.teatime;




import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test demos a user clicking on a GridView item in MenuActivity which opens up the
 * corresponding OrderActivity.
 *
 * This test does not utilize Idling Resources yet. If idling is set in the MenuActivity,
 * then this test will fail. See the IdlingResourcesTest for an identical test that
 * takes into account Idling Resources.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MenuActivityScreenTest {

    public static final String TEA_NAME = "Green Tea";

    private IdlingResource mIdlingResource;

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @Before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */
    @Rule
    public ActivityTestRule<MenuActivity> mActivityTestRule = new ActivityTestRule<>(MenuActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    /**
     * Clicks on a GridView item and checks it opens up the OrderActivity with the correct details.
     */

    @Test
    public void clickGridViewItem_OpensGreenTeaOrderActivity() {

        // Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific
        // gridview item and clicks it.
        onData(anything()).inAdapterView(withId(R.id.tea_grid_view)).atPosition(1).perform(click());

        // Checks that the OrderActivity opens with the correct tea name displayed
        onView(withId(R.id.tea_name_text_view)).check(matches(withText(TEA_NAME)));


    }
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
           // Espresso.unregisterIdlingResources(mIdlingResource);
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}