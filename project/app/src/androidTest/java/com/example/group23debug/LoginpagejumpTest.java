package com.example.group23debug;
import android.app.Activity;
import android.app.Instrumentation;
import android.widget.TextView;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.MonitoringInstrumentation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;
public class LoginpagejumpTest {
    @Rule
    public ActivityTestRule<Login> mActivityTestRule= new ActivityTestRule<Login>(Login.class);
    private Login mActivity=null;
    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(welcomAdm.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    public void LaunchSecondActivity() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.buttonlogin));
        onView(withId(R.id.emailid)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.passwordid)).perform(typeText("5T5ptQ"), closeSoftKeyboard());

        onView(withId(R.id.buttonlogin)).perform(click());
        Activity secondActivity=getInstrumentation().waitForMonitorWithTimeout(monitor, 8000);
        assertNotNull(secondActivity);
        secondActivity.finish();

    }
}
