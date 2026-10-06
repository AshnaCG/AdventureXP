<<<<<<<< HEAD:adventurexp/src/main/java/adventure/adventurexp/model/Equipment.java
package adventure.adventurexp.model;
========
package com.adventurealley.adventurexp.equipment;
>>>>>>>> 135b39884ccbec596f97b07f7a57b21c3f91463f:adventurexp/src/main/java/com/adventurealley/adventurexp/equipment/Equipment.java

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
    private EquipmentState equipmentState;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @JsonIgnore
    private Activity activity;

    public Equipment(){}

<<<<<<<< HEAD:adventurexp/src/main/java/adventure/adventurexp/model/Equipment.java
    public Equipment(long id, String name, boolean availability, EquipmentState equipmentState) {
        this.id = id;
========
    public Equipment(String name, boolean availability, State state) {
>>>>>>>> 135b39884ccbec596f97b07f7a57b21c3f91463f:adventurexp/src/main/java/com/adventurealley/adventurexp/equipment/Equipment.java
        this.name = name;
        this.availability = availability;
        this.equipmentState = equipmentState;
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

    public boolean isAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public EquipmentState getState() {
        return equipmentState;
    }
    public void setState(EquipmentState equipmentState) {
        this.equipmentState = equipmentState;
    }

    public Activity getActivity() { return activity; }
    public void setActivity(Activity activity) { this.activity = activity; }
}
