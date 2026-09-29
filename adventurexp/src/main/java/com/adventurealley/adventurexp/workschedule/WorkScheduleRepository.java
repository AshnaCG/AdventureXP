package com.adventurealley.adventurexp.workschedule;
import org.springframework.data.jpa.repository.JpaRepository;
import com.adventurealley.adventurexp.workschedule.WorkSchedule;

public interface WorkScheduleRepository  extends JpaRepository<WorkSchedule, Long> {
}
