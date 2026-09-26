package adventure.estera.adventurexp.model;

import jakarta.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    boolean availability;

    @Enumerated(EnumType.STRING)
    private EquipmentState equipmentState;

    public Equipment(){}

    public Equipment(long id, String name, boolean availability, EquipmentState equipmentState) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.equipmentState = equipmentState;
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
}
