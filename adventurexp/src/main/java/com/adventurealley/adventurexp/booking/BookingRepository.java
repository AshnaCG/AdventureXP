package com.adventurealley.adventurexp.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByOrderByStartTimeAsc();
    List<Booking> findByDate (LocalDate date);
}
