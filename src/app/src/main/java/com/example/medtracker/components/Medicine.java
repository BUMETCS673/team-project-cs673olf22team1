/**
 * @Author Andrew Gieraltowski
 * @Date 19/19/2022
 * @Brief The medicine object, used to track the dosage and frequency
 *        for taking medications
 */
package com.example.medtracker.components;

public class Medicine {

    private float dosage;           ///< The amount of this medication to take
    private Schedule schedule;      ///< The schedule associated with taking this medication
    private String name;            ///< Name of the medicine

    /**
     * @brief Constructor for the medicine class
     */
    public Medicine(float dosage, Schedule schedule, String name) {
        this.dosage = dosage;
        this.schedule = schedule;
        this.name = name;
    }

    /**
     * @brief Overloaded constructor
     */
    public Medicine() {
        this.dosage = 0;
        this.schedule = new Schedule();
        this.name = "";
    }

    /**
     * @brief Update the dosage of a medication
     */
    public void updateMedicationDosage(float dosage) {
        this.dosage = dosage;
    }

    /**
     * @brief Update the schedule of a medication
     *
     * @apiNote This should be used after calling getMedicationSchedule
     *          and preforming an update to the returned schedule
     */
    public void updateMedicationSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * @brief Getter for the medicine's dosage
     */
    public float getMedicationDosage() {
        return this.dosage;
    }

    /**
     * @brief Getter for the medicine's schedule
     */
    public Schedule getMedicationSchedule() {
        return this.schedule;
    }
}
