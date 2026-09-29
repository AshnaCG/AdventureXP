package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
