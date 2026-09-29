package adventure.estera.adventurexp.service;

import adventure.estera.adventurexp.controller.EquipmentController;
import adventure.estera.adventurexp.models.Equipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(EquipmentController.class)
public class EquipmentServiceTest {
    @Autowired
    MockMvcTester mvc;

    @MockitoBean
    EquipmentService equipmentService;

    @Test
    //@DisplayName("GET /api/todos - should return a todos list")

    //simpel starter test for service
    void checksServiceCanReturnList() {
        //arrange
        Equipment equipment1 = mock(Equipment.class);
        Equipment equipment2 = mock(Equipment.class);
        List<Equipment> equipmentList = List.of(equipment1, equipment2);
        //act
        when(equipmentService.getAll()).thenReturn(equipmentList);
        List<Equipment> actualEquipment = equipmentService.getAll();
        //assert
        assertEquals(actualEquipment, equipmentList);
        verify(equipmentService).getAll();


    }
}
