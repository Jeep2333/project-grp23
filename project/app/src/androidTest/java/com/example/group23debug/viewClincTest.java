package com.example.group23debug;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class viewClincTest {
    @Rule
    public ActivityTestRule<viewclinicemp> mActivityTestRule = new ActivityTestRule<viewclinicemp>(viewclinicemp.class);
    private viewclinicemp mActivity = null;
    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(AddClinic.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void LaunchSecondActivity() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.addclinicbtn));
        assertNotNull(mActivity.findViewById(R.id.cliniclistView));
        onView(withId(R.id.addclinicbtn)).perform(click());
        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 8000);
        assertNotNull(secondActivity);
        secondActivity.finish();

    }
}

