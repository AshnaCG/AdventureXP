package adventure.adventurexp.service;

import java.time.LocalDateTime;

import com.adventurealley.adventurexp.workschedule.Shift;
import com.adventurealley.adventurexp.workschedule.WorkScheduleDTO;
import com.adventurealley.adventurexp.workschedule.WorkScheduleDTOService;

import com.adventurealley.adventurexp.workschedule.WorkSchedule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkScheduleDTOServiceTests {

    @Test
    void shouldCreateWorkScheduleDTO() {

        // Arrange
        WorkScheduleDTOService dtoService = new WorkScheduleDTOService();

        Shift shift = new Shift(LocalDateTime.now(), LocalDateTime.now().plusHours(8));
        //shift.setShiftStart(LocalDateTime.now());
        //shift.setShiftEnd(LocalDateTime.now().plusHours(8));

        

        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setId(1L);
        workSchedule.setEmployeeId(42);


        workSchedule.getId();
        workSchedule.getEmployeeId();
        shift.getShiftStart();
        shift.getShiftEnd();

        // Act
        WorkScheduleDTO result =
                dtoService.createWorkScheduleDTO(workSchedule.getId(), workSchedule.getEmployeeId(), shift.getShiftStart(), shift.getShiftEnd());

        // Assert
        assertEquals(1L, result.id());
        assertEquals(42, result.employeeId());
    }
}