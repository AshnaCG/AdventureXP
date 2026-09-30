package com.adventurealley.adventurexp.workschedule;

import com.adventurealley.adventurexp.employee.Employee;
import java.time.LocalDateTime;



public class WorkScheduleDTOService {

    public long getWorkScheduleDataId(Employee employee) {
        return employee.getId();
    }

    public String getWorkScheduleDataName(Employee employee) {
        return employee.getName();
    }

    public Shift getWorkScheduleDataShiftById(Employee employee) {
        employee.getShift();
        return employee.getShift();
    }

    public WorkScheduleDTO createWorkScheduleDTO(Long id, long employeeId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {

        return new WorkScheduleDTO(
                id,
                employeeId,
                shiftStart,
                shiftEnd
        );}

}