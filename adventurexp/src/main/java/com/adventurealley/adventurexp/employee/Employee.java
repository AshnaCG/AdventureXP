package com.adventurealley.adventurexp.employee;

public class Employee {
    private int id;
    private String name;
    private Role role;

    public enum Role {
    EMPLOYEE,
    ADMIN
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getShift() {

    }
}