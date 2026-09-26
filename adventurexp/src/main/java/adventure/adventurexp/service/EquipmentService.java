package adventure.adventurexp.service;

import org.springframework.stereotype.Service;

import adventure.adventurexp.exception.NotFoundException;
import adventure.adventurexp.model.Equipment;
import adventure.adventurexp.repository.EquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }


    public Equipment create(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    public Equipment findById(Long id) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
        if (equipmentOptional.isEmpty()) {
            throw new NotFoundException("Equipment not found" + id);
        }
        return equipmentOptional.get();
    }

    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }
}
