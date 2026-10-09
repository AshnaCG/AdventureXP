package com.adventurealley.adventurexp.schedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController 
@RequestMapping("/Adventure/schedule")
public class ScheduleController {

    private final DayScheduleService dayScheduleService;

    public ScheduleController(DayScheduleService dayScheduleService) {
        this.dayScheduleService=dayScheduleService;
    }
    
    @GetMapping("/week")
    public List<DayScheduleDTO> getWeeklySchedule(@RequestParam LocalDate date) {
        return dayScheduleService.getWeekSchedule(date);
    }

    @GetMapping("/month")
    public List<DayScheduleDTO> getMonthlySchedule(@RequestParam LocalDate date) {
        return dayScheduleService.getMonthSchedule(date);
    }



}
