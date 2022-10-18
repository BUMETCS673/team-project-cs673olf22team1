package com.example.medtracker.viewmodel;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.medtracker.MedTracker;
import com.example.medtracker.database.MedTrackerDatabase;
import com.example.medtracker.database.MedicationDao;
import com.example.medtracker.database.entities.Medication;

import java.util.List;

public class MedListViewModel extends ViewModel {
    MedTrackerDatabase db = MedTrackerDatabase.getInstance(MedTracker.getAppContext());
    MedicationDao dao = db.medicationDao();

    private List<Medication> medList;

    public MedListViewModel() {
        refreshMeds();
        Log.d("ViewModel","MedListViewModel created" );
    }

    public void refreshMeds(){
        medList = dao.getAllMeds();
    }

    public void addMed(Medication medication) {
        dao.addMed(medication);
        refreshMeds();
    }

    public void deleteMed(Medication medication){
        dao.deleteMed(medication);
        refreshMeds();
    }

    public List<Medication> getAllMeds(){
        return medList;
    }

    public Medication searchMedId(int medId){
        return dao.getMedicationById(medId);
    }

    public List<Medication> searchMedName(String name){
        return dao.searchMedsByName(name);
    }


    public LiveData<Integer> getMedCount(){
        return dao.count();
    }

}