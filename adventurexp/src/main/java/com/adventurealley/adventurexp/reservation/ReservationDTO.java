package com.adventurealley.adventurexp.reservation;

import java.time.LocalDateTime;

public record ReservationDTO(
    String activity,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    LocalDateTime dateTime
)
{}
