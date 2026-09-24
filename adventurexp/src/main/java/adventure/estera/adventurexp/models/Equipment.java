package adventure.estera.adventurexp.models;

import jakarta.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    boolean availability;

    @Enumerated(EnumType.STRING)
    private State state;

    public Equipment(){}

    public Equipment(long id, String name, boolean availability, State state) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.state = state;
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

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}
