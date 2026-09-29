package adventure.adventurexp.service;

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

        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setId(1L);
        workSchedule.setEmployeeId(42);

        // Act
        WorkScheduleDTO result =
                dtoService.createWorkScheduleDTO(workSchedule);

        // Assert
        assertEquals(1L, result.id());
        assertEquals(42, result.employeeId());
    }
}