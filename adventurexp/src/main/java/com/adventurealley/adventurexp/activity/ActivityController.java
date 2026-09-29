package com.adventurealley.adventurexp.activity;

import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adventure/activity")
public class ActivityController {

    private final ActivityRepository activityRepository;

    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/{name}")
    public Activity getActivity(@PathVariable("name") String name) {
        return activityRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Activity not found: " + name));
    }
}