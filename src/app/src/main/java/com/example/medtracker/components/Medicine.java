/**
 * @Author Andrew Gieraltowski
 * @Date 09/19/2022
 * @Brief The medicine object, used to track the dosage and frequency
 *        for taking medications
 */
package com.example.medtracker.components;

public class Medicine {

    private float dosage;           ///< The amount of this medication to take
    private Schedule schedule;      ///< The schedule associated with taking this medication

    public Medicine(float dosage, Schedule schedule) {
        this.dosage = dosage;
        this.schedule = schedule;
    }

    private void updateMedicationDosage(float dosage)
    {
        this.dosage = dosage;
    }

    private void updateMedicationSchedule(Schedule schedule)
    {
        this.schedule = schedule;
    }

    private void updateMedication(float dosage, Schedule schedule)
    {
        this.dosage = dosage;
        this.schedule = schedule;
    }

    private float getMedicationDosage()
    {
        return this.dosage;
    }

    private Schedule getMedicationSchedule()
    {
        return this.schedule;
    }
}
