package com.example.medtracker;

import com.example.medtracker.components.frequencies.Daily;
import com.example.medtracker.components.frequencies.Hourly;
import com.example.medtracker.components.Schedule;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ScheduleUnitTest {

    @Test
    public void canAddTimeToSchedule() {
        // Set a custom time and frequency
        LocalDateTime time = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setTakeTime(time);
        assertEquals(schedule.getCurrentTakeTime(), time);
    }

    @Test
    public void canRemoveTimeFromSchedule() {
        //user doesn't want reminders anymore
        LocalDateTime time = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setTakeTime(time);

        //checks that a new time is set
        assertTrue(schedule.getCurrentTakeTime() == time);

        schedule.deleteTakeTime();

        //checks that time was deleted
        assertTrue(schedule.getCurrentTakeTime() == null);
    }

    @Test
    public void canUpdateHourlyReminder(){
        //once the current take time alarm goes off, the function to send the alarm will update the next take time
        LocalDateTime time = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setTakeTime(time);
        schedule.setFreq(new Hourly()); //take medication hourly
        //sets up a schedule and a custom reminder time (user inputs upon setting up medication or updating preferences)

        //checks that a new time is set
        assertTrue(schedule.getCurrentTakeTime() == time);

        // *** NOTIFICATION ENGINE: Sets off alarm, notifies the user, and updates the take time for the next reminder
        schedule.updateTakeTime();
        // take time should be updated to remind in the next hour
        assertEquals(time.plusHours(1), schedule.getCurrentTakeTime());
    }

    @Test
    public void canUpdateDailyReminder(){
        //once the current take time alarm goes off, the function to send the alarm will update the next take time
        LocalDateTime time = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setTakeTime(time);
        schedule.setFreq(new Daily()); //take medication daily
        //sets up a schedule and a custom reminder time (user inputs upon setting up medication or updating preferences)

        //checks that a new time is set
        assertTrue(schedule.getCurrentTakeTime() == time);

        // *** NOTIFICATION ENGINE: Sets off alarm, notifies the user, and updates the take time for the next reminder
        schedule.updateTakeTime();
        // take time should be updated to remind in the next day
        assertEquals(time.plusDays(1), schedule.getCurrentTakeTime());
    }

}