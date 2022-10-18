package com.example.medtracker.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.medtracker.components.frequencies.Frequency;
import com.example.medtracker.database.converters.DateTimeConverter;
import com.example.medtracker.database.converters.FrequencyConverter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Medication {


        public Medication(float dosage, Date reminder, String medName, Frequency frequency) {

                this.dosage = dosage;
                this.reminder = reminder;
                this.medName = medName;
                this.frequency = frequency;

        }
        @PrimaryKey(autoGenerate = true)
        public int medId;

        @ColumnInfo(name = "med_name")
        public String medName;

        @ColumnInfo(name = "dosage")
        public Float dosage;

        @ColumnInfo(name = "reminder")
        @TypeConverters(DateTimeConverter.class)
        public Date reminder;

        @ColumnInfo(name = "frequency")
        @TypeConverters(FrequencyConverter.class)
        public Frequency frequency;

}
