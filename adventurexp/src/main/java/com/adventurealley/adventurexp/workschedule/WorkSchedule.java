package com.adventurealley.adventurexp.workschedule;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.adventurealley.adventurexp.employee.Employee;
import com.adventurealley.adventurexp.workschedule.WorkSchedule.AttendanceState;
import com.adventurealley.adventurexp.workschedule.Shift;

import com.adventurealley.adventurexp.employee.Employee;
@Entity
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long employeeId;
    private long shiftId;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private AttendanceState attendanceState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    public WorkSchedule(){}

    public WorkSchedule(long id, long employeeId, long shiftId, LocalDateTime date,
                        AttendanceState attendanceState) {
        this.id = id;
        this.employeeId = employeeId;
        this.shiftId = shiftId;
        this.date = date;
        this.attendanceState = attendanceState;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Shift getShift() {
        return shift;
    }

   public enum AttendanceState {
        ACTIVE,
        SICK,
        VACATION,
        NO_SHOW
    }
}

