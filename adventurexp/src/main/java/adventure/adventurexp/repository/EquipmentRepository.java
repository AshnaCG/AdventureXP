package adventure.adventurexp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adventure.adventurexp.model.Equipment;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> id(long id);
}