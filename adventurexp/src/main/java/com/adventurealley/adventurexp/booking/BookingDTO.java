package com.adventurealley.adventurexp.booking;

import java.time.LocalDateTime;

public record BookingDTO(
        String guestName,
        LocalDateTime dateTime,
        String email,
        String phoneNumber,
        LocalDateTime startTime,
        int hourCount
) {
}
