package adventure.estera.adventurexp.model;

import jakarta.persistence.*;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String equipment;
    private String imageURL;
    private int durationMinutes;
    private int ageLimit;
    private int heightLimit;


    public Activity( String name, String description, String equipment, int durationMinutes, int age, double height) {
        this.name = name;
        this.description = description;
        this.equipment = equipment;
        this.imageURL = imageURL;
        this.durationMinutes = durationMinutes;
        this.ageLimit = age;
        this.heightLimit= heightLimit;
    }

    public Activity () {}

    public double getHeight() {
        return heightLimit;
    }

    public int getAge() {
        return ageLimit;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getEquipment() {
        return equipment;
    }
    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public void setHeightLimit(int heightLimit) {
        this.heightLimit = heightLimit;
    }
}
