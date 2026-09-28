package com.adventurealley.adventurexp.activity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adventure/activity")
public class ActivityController {

    @GetMapping
    public List<ActivityEnum> getAllActivities() {
        return List.of(ActivityEnum.values());
    }

    @GetMapping("/{name}")
    public ActivityEnum getActivity(@PathVariable String name) {
        return ActivityEnum.valueOf(name.toUpperCase());
    }
}