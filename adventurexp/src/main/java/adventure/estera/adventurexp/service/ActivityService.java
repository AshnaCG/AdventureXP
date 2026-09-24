package adventure.estera.adventurexp.service;

import adventure.estera.adventurexp.model.Activity;
import adventure.estera.adventurexp.repository.ActivityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class ActivityService {


    private final ActivityRepository repository;

        public ActivityService(ActivityRepository repository) {
            this.repository = repository;
        }
        public List<Activity> getAll() {
            return repository.findAll();
    }
        public Activity getOne(Long id) {
            return repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity with id" + id + "Not Found"));
        }


}
