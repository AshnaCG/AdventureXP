package com.adventurealley.adventurexp.service;
import com.adventurealley.adventurexp.equipment.Equipment;
import com.adventurealley.adventurexp.equipment.EquipmentRepository;
import com.adventurealley.adventurexp.equipment.EquipmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
//extend aktiverer mockito i klassen
@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {
    //laver service med dependencies
    @InjectMocks
    EquipmentService equipmentService;

    //fake repository
    @Mock
    EquipmentRepository equipmentRepository;





    @Test
    public void create() {
        //arrange
        // opretter en kasket
        Equipment equipment = new Equipment();
        equipment.setName("kasket");

        when(equipmentRepository.save(equipment)).thenReturn(equipment);

        //act
        //opretter med createmetode
        Equipment result = equipmentService.create(equipment);

        //assert
        // tjekker create = save
        assertThat(result).isEqualTo(equipment);

    }

    @Test
    public void findById(){
        //arrange
        Equipment equipment1 = new Equipment();
        equipment1.setName("hjul");

        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(equipment1));

        //act
        Equipment result = equipmentService.findById(1L);

        //assert
        assertThat(result).isEqualTo(equipment1);
    }

}
