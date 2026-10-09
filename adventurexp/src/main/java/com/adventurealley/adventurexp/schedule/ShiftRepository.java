package com.adventurealley.adventurexp.schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    List<Shift> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
