package com.adventurealley.adventurexp.schedule;

import com.adventurealley.adventurexp.employee.Employee;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Entity
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private LocalDate date;
    private Long employeeId;
    private String employeeName;
    


    public Shift() {
    }

    public Shift(LocalDateTime shiftStart, LocalDateTime shiftEnd, LocalDate date,
        Long employeeId, String employeeName) {
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.date = date;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDateTime getShiftStart() {
        return shiftStart;
    }
    public void setShiftStart(LocalDateTime shiftStart) {
        this.shiftStart = shiftStart;
    }
    public void setShiftEnd(LocalDateTime shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public LocalDateTime getShiftEnd() {
        return shiftEnd;
    }

    public LocalDate getDate() {
        return date;
    }

}
