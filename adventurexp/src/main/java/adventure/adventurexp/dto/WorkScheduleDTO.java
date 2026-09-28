package adventure.adventurexp.dto;


public record WorkScheduleDTO(
    Long id,
    String dayOfWeek,
    String startTime,
    String endTime;
    String name;
) {}