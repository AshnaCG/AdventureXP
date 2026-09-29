package com.adventurealley.adventurexp.workschedule;

import com.adventurealley.adventurexp.employee.Employee;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


@Entity
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shift")
    private List<WorkSchedule> workSchedules = new ArrayList<>();

    public List<WorkSchedule> getWorkSchedules() {
        return workSchedules;
    }

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;

    protected Shift() {
    }

    public long getId() {
        return id;
    }

}
