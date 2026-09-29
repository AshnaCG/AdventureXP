package com.adventurealley.adventurexp.reservation;

import com.adventurealley.adventurexp.activity.Activity;
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

    @ManyToOne
    private Activity activity;

    public Reservation() {
    }

    public Reservation(String guestName, LocalDateTime dateTime, String email, String phoneNumber, Activity activity) {
        this.guestName = guestName;
        this.dateTime = dateTime;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
