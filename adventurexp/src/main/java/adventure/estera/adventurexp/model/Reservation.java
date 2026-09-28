package adventure.estera.adventurexp.model;


import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;

    @Enumerated(EnumType.STRING)
    private ActivitiesEnum activities;

    public Reservation() {}

    public Reservation(String guestName, ActivitiesEnum activities) {
        this.guestName=guestName;
        this.activities=activities;
    }





}
