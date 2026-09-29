package adventure.estera.adventurexp.service;

import adventure.estera.adventurexp.model.Reservation;
import adventure.estera.adventurexp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository=reservationRepository;
    }

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
