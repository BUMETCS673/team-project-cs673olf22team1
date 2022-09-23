package com.example.medtracker;

import com.example.medtracker.components.Schedule;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ScheduleUnitTest {
    @Test
    public void canAddTimeToSchedule() {
        // Create a schedule object to operate in parallel with a list object
        LocalDateTime time = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.addTakeTime(time);

        List<LocalDateTime> LocalList = new ArrayList<>();
        LocalList.add(time);

        assertArrayEquals(schedule.getTakeTimes().toArray(), LocalList.toArray());
    }

    @Test
    public void canRemoveTimeFromSchedule() {
        LocalDateTime time = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.addTakeTime(time);

        assertTrue(schedule.getTakeTimes().size() > 0);

        schedule.deleteTakeTime(time);

        assertTrue(schedule.getTakeTimes().size() == 0);
    }
}