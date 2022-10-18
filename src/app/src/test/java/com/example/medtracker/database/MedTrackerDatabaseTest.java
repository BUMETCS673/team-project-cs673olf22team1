package com.example.medtracker.database;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import junit.framework.TestCase;

import org.junit.*;

import java.io.IOException;

public class MedTrackerDatabaseTest extends TestCase {
    private MedTrackerDatabase db;
    private MedicationDao medicationDao;

    @Before
    public void createDb() {

        // get context -- since this is an instrumental test it requires
        // context from the running application
        Context context = ApplicationProvider.getApplicationContext();
        // initialize the db and dao variable
        db = MedTrackerDatabase.getInstance(context);
        medicationDao = db.medicationDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void addPatient() {

        assertTrue(1==1);
    }

}