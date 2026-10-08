package com.adventurealley.adventurexp.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShiftDTO (
     String employeeName,
     int shiftStart,
     int shiftEnd,
     LocalDate date,
     String email,
     String phoneNumber

) {

}
