package com.example.medtracker.database;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.medtracker.database.entities.Account;

import junit.framework.TestCase;

import org.junit.*;

import java.io.IOException;

public class MedTrackerDatabaseTest extends TestCase {
    private MedTrackerDatabase db;
    private AccountDao accountDao;
    private MedicationDao medicationDao;

    @Before
    public void createDb() {

        // get context -- since this is an instrumental test it requires
        // context from the running application
        Context context = ApplicationProvider.getApplicationContext();
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, MedTrackerDatabase.class).build();
        accountDao = db.accountDao();
        medicationDao = db.medicationDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void addPatient() {
        Account a = new Account();
        a.firstName = "Bob";
        a.lastName = "Martin";
        a.dob = "1994/10/21";
        a.userType = "Patient";
        accountDao.addUser(a);

        Account patient = accountDao.findUser(a.firstName, a.lastName, a.dob);
        assertEquals(a.accountId, patient.accountId);
    }

}