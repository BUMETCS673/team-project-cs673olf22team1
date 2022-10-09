package com.example.medtracker.components.frequencies;

import java.time.LocalDateTime;

public interface Frequency {
    LocalDateTime updateTime(LocalDateTime oldTime);
    String toString();
}
