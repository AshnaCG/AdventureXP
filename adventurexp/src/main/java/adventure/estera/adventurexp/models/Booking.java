package adventure.estera.adventurexp.models;

import adventure.estera.adventurexp.enums.BookingChannel;
import adventure.estera.adventurexp.enums.BookingType;
import adventure.estera.adventurexp.model.ActivitiesEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
        private long id;

    @Enumerated(EnumType.STRING)
            private BookingType type;

    @Enumerated(EnumType.STRING)
        private BookingChannel channel;

    @Enumerated(EnumType.STRING)
        private ActivitiesEnum activity;

    private int participants;
    private LocalDateTime startTime;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;

    public Booking() {}

    public long getId() {
        return id;
    }

    public BookingType getType() {
        return type;
    }

    public BookingChannel getChannel() {
        return channel;
    }

    public ActivitiesEnum getActivity() {
        return activity;
    }

    public int getParticipants() {
        return participants;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(BookingType type) {
        this.type = type;
    }

    public void setChannel(BookingChannel channel) {
        this.channel = channel;
    }

    public void setActivity(ActivitiesEnum activity) {
        this.activity = activity;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}
