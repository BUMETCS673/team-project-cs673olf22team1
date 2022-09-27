package com.example.medtracker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.medtracker.database.entities.Account;
import com.example.medtracker.database.entities.Medication;

@Database(entities = {Account.class, Medication.class}, version = 1)
public abstract class MedTrackerDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
    public abstract MedicationDao medicationDao();
}