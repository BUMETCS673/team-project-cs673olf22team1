package com.example.medtracker.components.frequencies;

import java.time.LocalDateTime;

public class Hourly implements Frequency {
    @Override
    public LocalDateTime updateTime(LocalDateTime oldTime) {
        return oldTime.plusHours(1);
    }

    @Override
    public String toString(){
        return "Hourly";
    }
}
