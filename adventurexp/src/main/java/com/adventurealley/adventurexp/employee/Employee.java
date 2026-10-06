package com.adventurealley.adventurexp.employee;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.adventurealley.adventurexp.schedule.Shift;
import com.adventurealley.adventurexp.schedule.WorkSchedule;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String name;
    @OneToMany(mappedBy = "employee")
    private List<Shift> shifts = new ArrayList<>();



    public List<Shift> getShifts() {
        return shifts;
    }

    protected Employee() {
    }

    private Role role;

    public enum Role {
    EMPLOYEE,
    ADMIN
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shift getShift() {
        return Shift;
    }
}