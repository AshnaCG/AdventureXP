package com.adventurealley.adventurexp.service;


import static org.mockito.Mockito.mock;


import org.junit.jupiter.api.Test;

import com.adventurealley.adventurexp.schedule.Shift;
import com.adventurealley.adventurexp.schedule.ShiftRepository;
import com.adventurealley.adventurexp.schedule.ShiftService;

public class WorkScheduleDTOServiceTests {

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