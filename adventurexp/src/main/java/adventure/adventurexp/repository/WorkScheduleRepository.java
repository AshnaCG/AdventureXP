package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.model.Shift;
import adventure.estera.adventurexp.model.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepo  extends JpaRepository<WorkSchedule, Long> {
}
