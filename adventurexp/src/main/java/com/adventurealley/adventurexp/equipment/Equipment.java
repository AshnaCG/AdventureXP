package com.adventurealley.adventurexp.equipment;

import com.adventurealley.adventurexp.activity.Activity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean availability;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @JsonIgnore
    private Activity activity;

    public Equipment(){}

    public Equipment(String name, boolean availability, State state) {
        this.name = name;
        this.availability = availability;
        this.state = state;
    }


    //Så man stadig kan se hvilken aktivitet udstyret hører til i GET /equipment
    public String getActivityName() {
        return activity == null ? null : activity.getName();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean getAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public Activity getActivity() { return activity; }
    public void setActivity(Activity activity) { this.activity = activity; }
}
