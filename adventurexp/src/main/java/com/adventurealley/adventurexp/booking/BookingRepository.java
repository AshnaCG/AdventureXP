package com.adventurealley.adventurexp.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByOrderByStartTimeAsc();
    List<Booking> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
