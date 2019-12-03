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
import static org.junit.Assert.*;

public class welcomenorTest {
    @Rule
    public ActivityTestRule<welcomenor> mActivityTestRule= new ActivityTestRule<welcomenor>(welcomenor.class);
    private welcomenor mActivity=null;
    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(welcomenor.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    public void LaunchSecondActivity() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.button2));

        onView(withId(R.id.buttonlogin)).perform(click());
        Activity secondActivity=getInstrumentation().waitForMonitorWithTimeout(monitor, 8000);
        assertNotNull(secondActivity);
        secondActivity.finish();

    }

}