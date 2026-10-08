package com.adventurealley.adventurexp.booking;

import java.time.LocalDate;

public record BookingDTO(
        String guestName,
        LocalDate date,
        int startTime,
        int bookingDuration,
        String email,
        String phoneNumber
) {}
