package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.model.BusinessReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessReservationRepository extends JpaRepository<BusinessReservation, Long> {
}
