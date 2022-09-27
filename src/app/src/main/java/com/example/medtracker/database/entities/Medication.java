package com.example.medtracker.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "account_id",
        childColumns = "patient_id"))
public class Medication {

        @PrimaryKey(autoGenerate = true)
        public int medId;

        @ColumnInfo(name = "med_name")
        public String medName;

        @ColumnInfo(name = "priority")
        public Integer priority;

        @ColumnInfo(name = "image")
        public String image;

        @ColumnInfo(name = "dosage")
        public Float dosage;

        @ColumnInfo(name = "reminder")
        public String reminder;

        @ColumnInfo(name = "patient_id")
        public Integer patient;
}
