/**
 * @Author Andrew Gieraltowski
 * @Date 09/19/2022
 * @Brief The schedule object contains times and dates are which
 *        patient can choose to take their medication
 */
package com.example.medtracker.components;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<LocalDateTime> takeTimes;  ///< Array of dates and times to date a single medication

    public Schedule() {
        this.takeTimes = new ArrayList<>();
    }

    public void addTakeTime(LocalDateTime Time)
    {
        takeTimes.add(Time);
    }

    private void deleteTakeTime(LocalDateTime Time)
    {
        takeTimes.remove(Time);
    }

    public void updateTakeTime(LocalDateTime oldTime, LocalDateTime newTime)
    {
        this.deleteTakeTime(oldTime);
        this.addTakeTime(newTime);
    }
}
