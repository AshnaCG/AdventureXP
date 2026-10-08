package com.adventurealley.adventurexp.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookingDTO(
        String guestName,
        LocalDate date,
        String email,
        String phoneNumber,
        int startTime,
        int bookingDuration
) {
}
