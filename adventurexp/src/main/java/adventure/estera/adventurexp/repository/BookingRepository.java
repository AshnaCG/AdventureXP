package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByOrderByStartTimeAsc();
}
