package com.adventurealley.adventurexp.activity;

import com.adventurealley.adventurexp.equipment.Equipment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    private String imageURL;
    private int durationMinutes;
    private int minAge;
    private double minHeight;

    @OneToMany(mappedBy = "activity")
    @JsonIgnore
    private List<Equipment> equipment = new ArrayList<>();

    public List<String> getEquipmentTypes() {
        return equipment.stream()
                .map(Equipment::getName)
                .distinct()
                .toList();
    }

    public Activity(){}


    public Activity( String name, String description, String imageURL, int durationMinutes, int minAge, double minHeight) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.durationMinutes = durationMinutes;
        this.minAge = minAge;
        this.minHeight = minHeight;
    }


    // Hjælpemetode så begge sider af relationen altid er i sync
    public void addEquipment(Equipment e) {
        equipment.add(e);
        e.setActivity(this);
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }

    public double getMinHeight() { return minHeight; }
    public void setMinHeight(double minHeight) { this.minHeight = minHeight; }

    public List<Equipment> getEquipment() { return equipment; }
    public void setEquipment(List<Equipment> equipment) { this.equipment = equipment; }
}
