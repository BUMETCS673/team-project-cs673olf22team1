/**
 * @Author Andrew Gieraltowski
 * @Date 09/19/2022
 * @Brief The schedule object contains times and dates are which
 *        patient can choose to take their medication
 */
package com.example.medtracker.components;

import com.example.medtracker.components.frequencies.Frequency;

import java.time.*;

public class Schedule {

    private LocalDateTime nextTakeTime = null;
    private Frequency freq = null;

    public Schedule(){};

    public Schedule(LocalDateTime firstTime, Frequency freq) {
        this.nextTakeTime = firstTime;
        this.freq = freq;
    }

    public LocalDateTime getCurrentTakeTime() {
        return this.nextTakeTime;
    }

    public void deleteTakeTime() {this.nextTakeTime = null;}

    public void updateTakeTime() {
        LocalDateTime newTime = freq.updateTime(nextTakeTime);
        this.nextTakeTime = newTime;
    }

    public void setTakeTime(LocalDateTime customTime)
    {
        this.nextTakeTime = customTime;
    }

    public void setFreq(Frequency freq){
        this.freq = freq;
    }

}
