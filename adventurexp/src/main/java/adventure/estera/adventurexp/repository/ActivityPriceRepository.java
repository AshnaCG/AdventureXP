package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.model.ActivitiesEnum;
import adventure.estera.adventurexp.models.ActivityPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityPriceRepository extends JpaRepository<ActivityPrice, ActivitiesEnum> {}


