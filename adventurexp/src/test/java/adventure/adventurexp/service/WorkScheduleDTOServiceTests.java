package adventure.adventurexp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.adventurealley.adventurexp.schedule.Shift;
import com.adventurealley.adventurexp.schedule.ShiftRepository;
import com.adventurealley.adventurexp.schedule.ShiftService;
import com.adventurealley.adventurexp.schedule.WorkSchedule;
import com.adventurealley.adventurexp.schedule.WorkScheduleDTO;
import com.adventurealley.adventurexp.schedule.WorkScheduleDTOService;

public class WorkScheduleDTOServiceTests {

    @Test
    void shouldCreateDayScheduleDTO() {

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

    @Test
    void shouldCreateShiftDTO() {
        // Arrange
        ShiftRepository repo = mock(ShiftRepository.class);
        ShiftService service = new ShiftService(repo);
        Shift shift = new Shift();
        //Act
        service.createShift(shift);
        // Assert or verify
        service.getAllShifts();
        System.out.println("ole" + service.getAllShifts());
    }    
}