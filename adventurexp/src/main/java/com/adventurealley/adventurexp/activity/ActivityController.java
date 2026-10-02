package com.adventurealley.adventurexp.activity;

import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adventure/activity")
public class ActivityController {

    @GetMapping
    public List<ActivitiesEnum> getAllActivities() {
        return List.of(ActivitiesEnum.values());
    }

    @GetMapping("/{name}")
    public ActivitiesEnum getActivity(@PathVariable String name) {
        try {
            return ActivitiesEnum.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            {
                throw new NotFoundException("Aktivitet ikke fundet " + name);

            }
        }
    }
}