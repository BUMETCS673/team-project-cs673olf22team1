//package com.example.medtracker;
//
//import com.example.medtracker.components.Medicine;
//import com.example.medtracker.components.Schedule;
//
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.Assert.*;
//
//
//public class MedicineUnitTest {
//    @Test public void canUpdateMedicineSchedule() {
//        LocalDateTime time = LocalDateTime.now();
//        Schedule schedule = new Schedule();
//        schedule.addTakeTime(time);
//        Medicine medication = new Medicine(10, schedule);
//
//        assertEquals(medication.getMedicationSchedule().getTakeTimes(), schedule.getTakeTimes());
//
//        schedule.addTakeTime(LocalDateTime.now());
//        medication.updateMedicationSchedule(schedule);
//
//        assertEquals(medication.getMedicationSchedule().getTakeTimes(), schedule.getTakeTimes());
//    }
//
//    @Test public void canGetMedicineDosage() {
//        float dosage = 100;
//
//        LocalDateTime time = LocalDateTime.now();
//        Schedule schedule = new Schedule();
//        schedule.addTakeTime(time);
//        Medicine medication = new Medicine(dosage, schedule);
//
//        assertEquals(dosage, medication.getMedicationDosage(), 0);
//    }
//
//
//    @Test public void canUpdateMedicineDosage() {
//        float dosage = 100;
//        float newDosage = 200;
//        LocalDateTime time = LocalDateTime.now();
//        Schedule schedule = new Schedule();
//        schedule.addTakeTime(time);
//        Medicine medication = new Medicine(dosage, schedule);
//
//        medication.updateMedicationDosage(newDosage);
//
//        assertEquals(newDosage, medication.getMedicationDosage(), 0);
//    }
//}

