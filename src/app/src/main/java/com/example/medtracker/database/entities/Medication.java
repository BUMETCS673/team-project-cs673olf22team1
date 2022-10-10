package com.example.medtracker.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.medtracker.components.frequencies.Frequency;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Medication {


        public Medication(float dosage, Date reminder, String medName, Frequency frequency, int medId) {

                this.dosage = dosage;
                this.reminder = reminder;
                this.medName = medName;
                this.frequency = frequency;
                this.medId = medId;

        }
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
