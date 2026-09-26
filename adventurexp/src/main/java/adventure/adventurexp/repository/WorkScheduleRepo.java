package adventure.adventurexp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import adventure.adventurexp.model.WorkSchedule;

public interface WorkScheduleRepository  extends JpaRepository<WorkSchedule, Long> {
}
