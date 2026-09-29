package adventure.estera.adventurexp.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;
    private LocalDateTime dateTime;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ActivitiesEnum activities;

    public Reservation() {
    }

    public Reservation(String guestName, LocalDateTime dateTime, String email, String phoneNumber, ActivitiesEnum activities) {
        this.guestName = guestName;
        this.dateTime = dateTime;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActivitiesEnum getActivities() {
        return activities;
    }

    public void setActivities(ActivitiesEnum activities) {
        this.activities = activities;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
}
