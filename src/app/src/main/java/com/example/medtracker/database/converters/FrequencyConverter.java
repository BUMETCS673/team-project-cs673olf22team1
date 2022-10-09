package com.example.medtracker.database.converters;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.example.medtracker.components.frequencies.Daily;
import com.example.medtracker.components.frequencies.Frequency;
import com.example.medtracker.components.frequencies.Hourly;

@ProvidedTypeConverter
public class FrequencyConverter {
    @TypeConverter
    public Frequency StringToFrequency(String string) {
        Frequency freq = null;
        if (string.equals(Daily.class.toString())){
            freq = new Daily();
        }
        if (string.equals(Hourly.class.toString())){
            freq = new Hourly();
        }
        return freq;
    }

    @TypeConverter
    public String FrequencyToString(Frequency frequency){
        return frequency.toString();
    }
}