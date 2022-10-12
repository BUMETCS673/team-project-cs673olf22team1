package com.example.medtracker.database;

import android.content.Context;

import androidx.room.Room;

public class MedTrackerDatabaseSingleton {
    private static MedTrackerDatabase instance = null;
    private MedTrackerDatabaseSingleton() {}

    public static MedTrackerDatabase getInstance(Context context) {
        if (instance != null){
            return instance;
        }
        instance = Room.databaseBuilder(context, MedTrackerDatabase.class, "medtrackerdb").build();
        return instance;
    }

}
