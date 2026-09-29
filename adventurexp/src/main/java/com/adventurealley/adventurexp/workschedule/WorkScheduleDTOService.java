    package com.adventurealley.adventurexp.workschedule;

    import com.adventurealley.adventurexp.employee.Employee;

public class WorkScheduleDTOService{

    

    public int getWorkScheduleDataId(Employee employee){
        return employee.getId();
    }


    public String getWorkScheduleDataName(Employee employee){
        return employee.getName();
    }

    public shift getWorkScheduleDataShiftById(Employee employee){
        return employee.getShift();
    }


    public workScheduleDTO createWorkScheduleDTO(WorkSchedule workSchedule){
        workSchedule.getId();
        workSchedule.getEmployeeId();

        WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO(
                workSchedule.getId(),
                workSchedule.getEmployeeId());
        return workScheduleDTO;
    }

}