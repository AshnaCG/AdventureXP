package com.adventurealley.adventurexp.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShiftDTO (
     String employeeName,
     LocalDateTime shiftStart,
     LocalDateTime shiftEnd,
     LocalDate date,
     String guestName,
     LocalDateTime dateTime,
     String email,
     String phoneNumber,
     LocalDateTime bookingStart,
     int bookingDuration
) {

}
