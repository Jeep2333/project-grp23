package com.example.group23debug;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class Signtest {
    @Rule
    public ActivityTestRule<SignUp> mActivityTestRule= new ActivityTestRule<SignUp>(SignUp.class);
    private SignUp mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkSignUp()throws Exception{
        assertNotNull(mActivity.findViewById(R.id.editText));
        assertNotNull(mActivity.findViewById(R.id.editText2));
        assertNotNull(mActivity.findViewById(R.id.editText3));
        assertNotNull(mActivity.findViewById(R.id.editText4));
        assertNotNull(mActivity.findViewById(R.id.editText5));
        assertNotNull(mActivity.findViewById(R.id.editText6));
        assertNotNull(mActivity.findViewById(R.id.textView9));
        assertNotNull(mActivity.findViewById(R.id.switch1));
        assertNotNull(mActivity.findViewById(R.id.textView10));
        assertNotNull(mActivity.findViewById(R.id.button));
        assertNotNull(mActivity.findViewById(R.id.textView2));
    }
}


