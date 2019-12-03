package com.example.group23debug;

import android.widget.TextView;


import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
//import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;



    public class addserviceTest {
        @Rule
        public ActivityTestRule<AddService> mActivityTestRule = new ActivityTestRule<AddService>(AddService.class);
        private AddService mActivity = null;
        private TextView text;

        @Before
        public void setUp() throws Exception {
            mActivity = mActivityTestRule.getActivity();
        }

        @Test

        public void checkadd() throws Exception {
            assertNotNull(mActivity.findViewById(R.id.addtextname));
            assertNotNull(mActivity.findViewById(R.id.addtextrole));
            assertNotNull(mActivity.findViewById(R.id.addbutton));

        }
    }