package com.example.medtracker.controller;

import static org.junit.Assert.assertNotNull;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.medtracker.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class mainTest {

    @Rule
    public ActivityTestRule<main> mActivityTestRule = new ActivityTestRule<>(main.class);

    private main mActivity = null;


    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }

    /**
     * @Test Test if the acitivity is launch successful
     */
    @Test
    public void testLaunch() {

        View view = mActivity.findViewById(R.id.editTextTextPersonName2);

        assertNotNull(view);

    }



    @After
    public void tearDown() throws Exception {

        mActivity = null;

    }
}