package com.adventurealley.adventurexp.workschedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/Adventure/schedule")
public class ScheduleController {
    
    @GetMapping
    public String getSchedule() {
        return "This is the schedule endpoint.";
    }
}
