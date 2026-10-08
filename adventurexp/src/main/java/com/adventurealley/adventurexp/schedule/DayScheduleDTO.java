package com.adventurealley.adventurexp.schedule;

import com.adventurealley.adventurexp.booking.Booking;
import com.adventurealley.adventurexp.booking.BookingDTO;

import java.util.List;
import java.time.LocalDate;

public record DayScheduleDTO(
    LocalDate date,
    List<ShiftDTO> shifts,
    List<BookingDTO> bookings
) {

}
