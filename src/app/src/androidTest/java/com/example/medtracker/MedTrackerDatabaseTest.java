package com.example.medtracker;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.medtracker.MedTracker;
import com.example.medtracker.database.MedTrackerDatabase;

import junit.framework.TestCase;

import org.junit.*;

import java.io.IOException;

public class MedTrackerDatabaseTest {

    MedTrackerDatabase db;

    @Test
    public void verifyDB() {
        db = MedTrackerDatabase.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        MedTrackerDatabase newdb = MedTrackerDatabase.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        assertEquals(db, newdb);
    }

}