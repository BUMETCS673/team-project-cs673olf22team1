package com.example.medtracker.viewmodel;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.RoomDatabase;

import com.example.medtracker.MedTracker;
import com.example.medtracker.components.frequencies.Frequency;
import com.example.medtracker.database.MedTrackerDatabase;
import com.example.medtracker.database.MedicationDao;
import com.example.medtracker.database.entities.Medication;
import java.util.Date;

public class CurMedViewModel extends ViewModel {
    MedTrackerDatabase db = MedTrackerDatabase.getInstance(MedTracker.getAppContext());
    MedicationDao dao = db.medicationDao();
    MutableLiveData<Medication> curMed;

    public CurMedViewModel() {
        Log.d("ViewModel","CurMedViewModel created" );
    }

    void initCurMed(Medication medication){
        if (curMed.getValue() == null){
            curMed.setValue(medication);
        }
    }

    void setCurMed(Medication medication){
        curMed.setValue(medication);
    }

    void updateCurMed(
            String name,
            Float dosage,
            Date reminder,
            Frequency frequency
    ){
        curMed.getValue().medName = name;
        curMed.getValue().dosage = dosage;
        curMed.getValue().reminder = reminder;
        curMed.getValue().frequency = frequency;

        dao.editMed(curMed.getValue());
    }

    void deleteCurMed(){
        dao.deleteMed(curMed.getValue());
        curMed.setValue(null);
    }
}