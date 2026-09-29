package com.adventurealley.adventurexp.workschedule;
import java.time.LocalDateTime;


public record WorkScheduleDTO(
     Long id,
     long employeeId,
     LocalDateTime shiftStart,
     LocalDateTime shiftEnd){
     }
