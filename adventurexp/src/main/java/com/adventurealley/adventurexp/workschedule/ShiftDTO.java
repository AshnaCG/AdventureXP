package com.adventurealley.adventurexp.workschedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShiftDTO (
     LocalDateTime shiftStart,
     LocalDateTime shiftEnd,
     LocalDate date
) {

}
