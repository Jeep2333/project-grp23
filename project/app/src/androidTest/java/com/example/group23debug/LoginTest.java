package com.example.group23debug;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LoginTest {
    @Rule
    public ActivityTestRule<Login> mActivityTestRule= new ActivityTestRule<Login>(Login.class);
    private Login mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checklogin() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.emailid));
        assertNotNull(mActivity.findViewById(R.id.buttonlogin));
        assertNotNull(mActivity.findViewById(R.id.textView6));
        assertNotNull(mActivity.findViewById(R.id.passwordid));
        assertNotNull(mActivity.findViewById(R.id.showpassword));

    }



}
