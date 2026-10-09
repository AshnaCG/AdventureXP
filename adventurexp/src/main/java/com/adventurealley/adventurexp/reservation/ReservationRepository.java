package com.adventurealley.adventurexp.reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository 
extends JpaRepository<Reservation, Long> {
    
List<Reservation> findByActivity_Name(String activityName);
}