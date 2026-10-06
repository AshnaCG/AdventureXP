package com.adventurealley.adventurexp.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

     Reservation findAllById(Long id);
    void update(Reservation entity);
    void deleteById(Long id);
    void filterByActivity(String activityName);

}
