package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
