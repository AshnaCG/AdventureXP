package com.adventurealley.adventurexp.booking;

import com.adventurealley.adventurexp.activity.ActivitiesEnum;

import jakarta.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

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
    private int hourCount;
    private boolean cancelled;

    @ManyToOne
    private BookingPackage bookingPackage;

    private String packageContents;
    private int totalPrice;

    public Booking() {}

    public Long getId() {
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

    public BookingPackage getBookingPackage() {
        return bookingPackage;
    }

    public String getPackageContents() {
        return packageContents;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setId(Long id) {
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

    public int getHourCount() {
        return hourCount;
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

    public void setBookingPackage(BookingPackage bookingPackage) {
        this.bookingPackage = bookingPackage;
    }

    public void setPackageContents(String packageContents) {
        this.packageContents = packageContents;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public interface BookingRepository extends JpaRepository<Booking, Long> {
        List<Booking> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    }

    public void setHourCount(int hourCount) {
        this.hourCount = hourCount;
    }
}