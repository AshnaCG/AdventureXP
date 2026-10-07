package com.adventurealley.adventurexp.schedule;

import java.util.List;
import java.time.YearMonth;

public record MonthScheduleDTO(YearMonth month, List<DayScheduleDTO> daySchedules) {
}
