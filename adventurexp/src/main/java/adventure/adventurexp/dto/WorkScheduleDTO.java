package adventure.adventurexp.dto;
import adventure.adventurexp.model.Employee;
import adventure.adventurexp.model.Shift;

public record WorkScheduleDTO(
     Long id,
     Shift shift,
     Employee employee){

     }
