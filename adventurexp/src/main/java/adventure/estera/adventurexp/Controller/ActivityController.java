package adventure.estera.adventurexp.Controller;

import adventure.estera.adventurexp.model.ActivitiesEnum;
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
        return ActivitiesEnum.valueOf(name.toUpperCase());
    }
}