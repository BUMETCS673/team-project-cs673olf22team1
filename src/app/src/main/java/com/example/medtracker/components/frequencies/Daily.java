package com.example.medtracker.components.frequencies;

import java.time.LocalDateTime;

public class Daily implements Frequency{
    @Override
    public LocalDateTime updateTime(LocalDateTime oldTime) {
        return oldTime.plusDays(1);
    }

    @Override
    public String toString(){
        return "Daily";
    }
}
