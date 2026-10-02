package com.adventurealley.adventurexp.workschedule;

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

    @OneToMany(mappedBy = "shift")
    private List<WorkSchedule> workSchedules = new ArrayList<>();

    public List<WorkSchedule> getWorkSchedules() {
        return workSchedules;
    }

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private LocalDate date;
    


    public Shift() {
    }

    public Shift(LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public long getId() {
        return id;
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

}
