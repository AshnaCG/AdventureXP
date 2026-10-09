package com.adventurealley.adventurexp.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShiftDTO(
        String employeeName,
        LocalDate date,
        int shiftStart,
        int shiftEnd,
        String email,
        String phoneNumber
) {}


