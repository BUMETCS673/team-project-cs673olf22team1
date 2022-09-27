package com.example.medtracker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.medtracker.database.entities.Account;
import com.example.medtracker.database.entities.Medication;

import java.util.List;


@Dao
public interface MedicationDao {
    @Query("SELECT * FROM Medication")
    List<Medication> getAllMeds();

    @Query("SELECT * FROM Medication WHERE medId = :medId")
    Medication getMedicationById(int medId);

    @Query("SELECT * FROM Medication WHERE med_name LIKE :medName")
    List<Medication> searchMedsByName(String medName);

    @Query("SELECT * FROM Medication WHERE patient_id = :patientId")
    List<Medication> getPatientMeds(int patientId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMeds(Medication... medications);

    @Delete
    void deleteMed(Medication medication);
}