package com.adventurealley.adventurexp.activity;

import com.adventurealley.adventurexp.equipment.Equipment;
import com.adventurealley.adventurexp.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class ActivityService {

    public final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity create(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public Activity findById(Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        if (activityOptional.isEmpty()) {
            throw new NotFoundException("Activity not found" + id);

        }
        return activityOptional.get();
    }


    public Activity update(Long id, Activity updated){
        Activity existing = findById(id);

        existing.setName(updated.getName().trim());
        existing.setDescription(updated.getDescription());
        existing.setImageURL(updated.getImageURL());
        existing.setDurationMinutes(updated.getDurationMinutes());
        existing.setMinAge(updated.getMinAge());
        existing.setMinHeight(updated.getMinHeight());

        return activityRepository.save(existing);

    }

    @Transactional
    public void delete(Long id) {
        Activity activity = findById(id);
        for (Equipment e : new ArrayList<>(activity.getEquipment())) {


            e.setActivity(null);
        }
            activity.getEquipment().clear();
        activityRepository.delete(activity);
    }


}
