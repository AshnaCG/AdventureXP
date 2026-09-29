package adventure.adventurexp.dto;
import java.time.LocalDateTime;


public record WorkScheduleDTO(
     Long id,
     long employeeId,
     LocalDateTime shiftStart,
     LocalDateTime shiftEnd){
     }
