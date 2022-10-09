package com.example.medtracker.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.medtracker.components.frequencies.Frequency;

import java.util.Date;

@Entity
public class Medication {

        @PrimaryKey(autoGenerate = true)
        public int medId;

        @ColumnInfo(name = "med_name")
        public String medName;

        @ColumnInfo(name = "dosage")
        public Float dosage;

        @ColumnInfo(name = "reminder")
        public Date reminder;

        @ColumnInfo(name = "frequency")
        public Frequency frequency;
}
