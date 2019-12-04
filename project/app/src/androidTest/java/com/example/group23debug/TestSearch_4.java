package com.example.group23debug;

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class TestSearch_4 {
    @Rule
    public ActivityTestRule<Search> mActivityTestRule = new ActivityTestRule<Search>(Search.class);
    private Search mActivity = null;
//    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Search.class.search(), null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void LaunchSecondActivity() throws Exception {
        
        assertNotNull(mActivity.findViewById(R.id.clist));
        

    }
}