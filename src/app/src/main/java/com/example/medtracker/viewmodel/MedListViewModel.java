package com.example.medtracker.viewmodel;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.medtracker.database.MedTrackerDatabase;
import com.example.medtracker.database.MedTrackerDatabaseSingleton;
import com.example.medtracker.database.MedicationDao;
import com.example.medtracker.database.entities.Medication;

import java.util.List;
import java.util.concurrent.Executors;

public class MedListViewModel extends ViewModel {
    MedTrackerDatabase db = MedTrackerDatabaseSingleton.getInstance(getApplicationContext());
    MedicationDao dao = db.medicationDao();

    private LiveData<List<Medication>> medList = dao.getAllMeds();

    LiveData<List<Medication>>  getAllHabits(){
        return dao.getAllMeds();
    }

    void addMed(Medication medication) {
        dao.addMed(medication);
    }

    LiveData<Medication> searchMedId(int medId){
        return dao.getMedicationById(medId);
    }

    LiveData<List<Medication>> searchMedName(String name){
        return dao.searchMedsByName(name);
    }


    LiveData<Integer> getMedCount(){
        return dao.count();
    }

}
