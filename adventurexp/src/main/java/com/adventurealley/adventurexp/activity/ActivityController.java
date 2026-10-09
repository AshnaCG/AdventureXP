package com.adventurealley.adventurexp.activity;

import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adventure/activity")
public class ActivityController {

    private final ActivityRepository activityRepository;
    private final ActivityService activityService;

    public ActivityController(ActivityRepository activityRepository, ActivityService activityService) {
        this.activityRepository = activityRepository;
        this.activityService = activityService;
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

    @GetMapping("/id/{id}")
    public ResponseEntity<Activity> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(activityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Activity> create(@RequestBody Activity activity){
        return ResponseEntity.ok(activityService.create(activity));

    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Activity> deleteById(@PathVariable("id") long id){
        activityService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Activity> updateById(@PathVariable ("id") long id,
                                               @RequestBody Activity activity){

        activityService.update(id,activity);
        return ResponseEntity.ok(activity);




    }


}