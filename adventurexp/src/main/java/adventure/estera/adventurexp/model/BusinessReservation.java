package adventure.estera.adventurexp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BusinessReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String contactPerson;
    private String phoneNumber;
    private LocalDateTime reservationTime;
    private int participants;

    public BusinessReservation() {}

    public BusinessReservation(String companyName, String contactPerson, String phoneNumber, LocalDateTime reservationTime, int participants) {
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.reservationTime = reservationTime;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getParticipants() {
        return participants;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}
