package adventure.adventurexp.service;

import adventure.adventurexp.model.Employee;

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

    public workScheduleDTO createWorkScheduleDTO(){
        getWorkScheduleDataId(new Employee());
        getWorkScheduleDataName(new Employee());
    }

}