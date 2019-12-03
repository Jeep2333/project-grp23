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

public class welcom_staffTest {
    @Rule
    public ActivityTestRule<welcom_staff> mActivityTestRule = new ActivityTestRule<welcom_staff>(welcom_staff.class);
    private welcom_staff mActivity = null;
    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(viewclinicemp.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void LaunchSecondActivity() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.staffclinicbtn));
        assertNotNull(mActivity.findViewById(R.id.statextView32));
        assertNotNull(mActivity.findViewById(R.id.staffname));
        assertNotNull(mActivity.findViewById(R.id.staffsype));
        assertNotNull(mActivity.findViewById(R.id.stafffname));
        assertNotNull(mActivity.findViewById(R.id.stafflname));
        assertNotNull(mActivity.findViewById(R.id.staffemail));
        assertNotNull(mActivity.findViewById(R.id.timelistbtn));
        assertNotNull(mActivity.findViewById(R.id.stafflogout));






    }
}
