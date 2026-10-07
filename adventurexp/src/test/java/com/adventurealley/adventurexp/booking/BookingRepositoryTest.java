package com.adventurealley.adventurexp.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository repository;

    private Booking createBooking(String customerName, String phoneNumber,
                                  LocalDateTime startTime, int participants) {
        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setCustomerPhoneNumber(phoneNumber);
        booking.setStartTime(startTime);
        booking.setParticipants(participants);
        booking.setType(BookingType.WHOLE_CENTER);
        booking.setChannel(BookingChannel.MANUAL);
        return booking;
    }

    @Test
    void savedBookingGetsAnId() {
        Booking saved = repository.save(createBooking(
                "Firma A", "12345678",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40));

        assertNotNull(saved.getId());
    }

    @Test
    void savedBookingCanBeFoundAgainWithSameValues() {
        Booking saved = repository.save(createBooking(
                "Firma A", "12345678",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40));

        Booking found = repository.findById(saved.getId()).orElseThrow();

        assertEquals("Firma A", found.getCustomerName());
        assertEquals("12345678", found.getCustomerPhoneNumber());
        assertEquals(LocalDateTime.of(2026, 11, 20, 10, 0), found.getStartTime());
        assertEquals(40, found.getParticipants());
        assertEquals(BookingType.WHOLE_CENTER, found.getType());
        assertEquals(BookingChannel.MANUAL, found.getChannel());
    }

    @Test
    void findAllReturnsAllSavedBookings() {
        repository.save(createBooking("Firma A", "11111111",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40));
        repository.save(createBooking("Firma B", "22222222",
                LocalDateTime.of(2026, 12, 1, 12, 0), 60));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void deletedBookingCannotBeFound() {
        Booking saved = repository.save(createBooking(
                "Firma A", "11111111",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40));

        repository.deleteById(saved.getId());

        assertTrue(repository.findById(saved.getId()).isEmpty());
    }
}

