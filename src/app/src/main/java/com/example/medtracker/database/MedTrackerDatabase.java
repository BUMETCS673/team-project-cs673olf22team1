package com.example.medtracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medtracker.database.converters.DateTimeConverter;
import com.example.medtracker.database.converters.FrequencyConverter;
import com.example.medtracker.database.entities.Medication;

@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class MedTrackerDatabase extends RoomDatabase {

    public abstract MedicationDao medicationDao();
    private static MedTrackerDatabase INSTANCE;

    public static synchronized MedTrackerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = create(context);
        }
        return INSTANCE;
    }


    protected MedTrackerDatabase() {}

    private static MedTrackerDatabase create(final Context context){
        FrequencyConverter frequencyConverter = new FrequencyConverter();
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        return Room.databaseBuilder(
                context,
                MedTrackerDatabase.class,
                "medtrackerdb")
                .allowMainThreadQueries()
                .addTypeConverter(frequencyConverter)
                .build();
    }

}