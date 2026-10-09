package com.adventurealley.adventurexp.employee;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.adventurealley.adventurexp.schedule.Shift;


@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Role role;
    private String name;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "employee")
    private List<Shift> shifts = new ArrayList<>();



    public List<Shift> getShifts() {
        return shifts;
    }

    protected Employee() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}