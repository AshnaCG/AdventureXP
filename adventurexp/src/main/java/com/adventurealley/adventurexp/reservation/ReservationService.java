package com.adventurealley.adventurexp.reservation;

import org.springframework.stereotype.Service;
import java.util.List;
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

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(
        Long id, Reservation updatedReservation) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Reservation not found"));

        reservation.setGuestName(updatedReservation.getGuestName());
        reservation.setDateTime(updatedReservation.getDateTime());
        reservation.setEmail(updatedReservation.getEmail());
        reservation.setPhoneNumber(updatedReservation.getPhoneNumber());
        reservation.setActivity(updatedReservation.getActivity());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> filterByActivity(String activityName) {
        return reservationRepository.findActivityName(activityName);
    }

}


