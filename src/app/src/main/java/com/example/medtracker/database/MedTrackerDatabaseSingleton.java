package com.example.medtracker.database;

import android.content.Context;

import androidx.room.Room;

import com.example.medtracker.database.converters.DateTimeConverter;
import com.example.medtracker.database.converters.FrequencyConverter;

public class MedTrackerDatabaseSingleton {
    private static MedTrackerDatabase instance = null;
    private MedTrackerDatabaseSingleton() {}

    public static MedTrackerDatabase getInstance(Context context) {
        if (instance != null){
            return instance;
        }
        FrequencyConverter frequencyConverter = new FrequencyConverter();
        instance = Room.databaseBuilder(context, MedTrackerDatabase.class, "medtrackerdb")
                .allowMainThreadQueries()
                .addTypeConverter(frequencyConverter)
                .build();
        return instance;
    }

}
