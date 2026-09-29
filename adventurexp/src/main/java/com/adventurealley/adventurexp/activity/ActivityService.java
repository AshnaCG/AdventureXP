package com.adventurealley.adventurexp.activity;

import com.adventurealley.adventurexp.equipment.Equipment;
import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public Activity findByName(String name) {
        return activityRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Activity not found: " + name));
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Activity not found: " + id));
    }

    public Activity create(Activity activity) {
        validate(activity, null);
        activity.setId(null); // id styres af databasen
        activity.setName(activity.getName().trim());
        return activityRepository.save(activity);
    }

    // Opdaterer kun de felter medarbejderen kan redigere - udstyrsrelationen bevares
    public Activity update(Long id, Activity updated) {
        Activity existing = findById(id);
        validate(updated, id);

        existing.setName(updated.getName().trim());
        existing.setDescription(updated.getDescription());
        existing.setImageURL(updated.getImageURL());
        existing.setDurationMinutes(updated.getDurationMinutes());
        existing.setMinAge(updated.getMinAge());
        existing.setMinHeight(updated.getMinHeight());

        return activityRepository.save(existing);
    }

    // Udstyret slettes ikke sammen med aktiviteten - det kobles fri og vises som "Ingen aktivitet"
    @Transactional
    public void delete(Long id) {
        Activity activity = findById(id);
        for (Equipment e : new ArrayList<>(activity.getEquipment())) {
            e.setActivity(null);
        }
        activity.getEquipment().clear();
        activityRepository.delete(activity);
    }

    private void validate(Activity activity, Long currentId) {
        if (activity.getName() == null || activity.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Navn skal udfyldes");
        }
        if (activity.getDurationMinutes() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Varighed skal være større end 0");
        }
        if (activity.getMinAge() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aldersgrænse kan ikke være negativ");
        }
        if (activity.getMinHeight() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Minimumshøjde kan ikke være negativ");
        }
        // Navnet bruges til opslag (GET /adventure/activity/{name}), så det skal være unikt
        activityRepository.findByNameIgnoreCase(activity.getName().trim())
                .filter(other -> !other.getId().equals(currentId))
                .ifPresent(other -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Der findes allerede en aktivitet med navnet " + other.getName());
                });
    }
}
