package com.adventurealley.adventurexp.schedule;

import java.util.List;
import java.time.LocalDate;

public record DayScheduleDTO(
    LocalDate date,
    List<ShiftDTO> shifts
) {

}
