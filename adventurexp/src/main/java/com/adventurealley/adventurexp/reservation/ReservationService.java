package com.adventurealley.adventurexp.reservation;

import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        if (reservation.getDateTime()
                .isAfter(LocalDateTime.now().plusHours(24))) {

            reservation.cancel();
            reservationRepository.save(reservation);
        }



    }
}
