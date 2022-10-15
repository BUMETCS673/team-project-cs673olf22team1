package com.example.medtracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.medtracker.components.Medicine;
import com.example.medtracker.database.entities.Medication;

import java.util.List;


@Dao
public interface MedicationDao {
    @Query("SELECT * FROM Medication")
    LiveData<List<Medication>> getAllMeds();

    @Query("SELECT * FROM Medication WHERE medId = :medId")
    LiveData<Medication> getMedicationById(int medId);

    @Query("SELECT * FROM Medication WHERE med_name LIKE :medName")
    LiveData<List<Medication>> searchMedsByName(String medName);

    @Delete
    void deleteMed(Medication medication);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMed(Medication medication);

    @Update
    void editMed(Medication medication);

    @Query("SELECT count(*) From Medication")
    LiveData<Integer> count();
}