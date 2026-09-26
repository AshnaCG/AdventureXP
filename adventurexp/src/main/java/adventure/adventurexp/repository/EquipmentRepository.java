package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> id(long id);
}
