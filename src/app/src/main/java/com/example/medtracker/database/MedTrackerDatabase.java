package com.example.medtracker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.medtracker.database.converters.DateTimeConverter;
import com.example.medtracker.database.converters.FrequencyConverter;
import com.example.medtracker.database.entities.Medication;

@Database(entities = {Medication.class}, version = 1)
@TypeConverters({DateTimeConverter.class, FrequencyConverter.class})
public abstract class MedTrackerDatabase extends RoomDatabase {
    public abstract MedicationDao medicationDao();
}