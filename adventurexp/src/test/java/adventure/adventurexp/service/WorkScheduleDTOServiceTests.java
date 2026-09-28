    package adventure.adventurexp.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import adventure.adventurexp.dto.WorkScheduleDTO;
import adventure.adventurexp.repository.WorkScheduleRepository;

public record WorkScheduleDTOServiceTests(){


        @Test 
        void shouldgetEmployeeIdFromWorkSchedule(){
            //arrange
            WorkScheduleRepository repo = mock(WorkScheduleRepository.class);
            WorkScheduleDTOService DTOservice = new WorkScheduleDTOService();
            WorkScheduleService service = new WorkScheduleService(repo);
            WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO();
            //act
            service.createWorkScheduleDTO(workScheduleDTO);
        }

    }