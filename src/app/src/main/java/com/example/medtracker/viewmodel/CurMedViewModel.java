package com.example.medtracker.viewmodel;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.medtracker.components.frequencies.Frequency;
import com.example.medtracker.database.MedTrackerDatabase;
import com.example.medtracker.database.MedTrackerDatabaseSingleton;
import com.example.medtracker.database.MedicationDao;
import com.example.medtracker.database.entities.Medication;

import java.util.Date;

public class CurMedViewModel extends ViewModel {
    MedTrackerDatabase db = MedTrackerDatabaseSingleton.getInstance(getApplicationContext());
    MedicationDao dao = db.medicationDao();
    MutableLiveData<Medication> curMed;

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
