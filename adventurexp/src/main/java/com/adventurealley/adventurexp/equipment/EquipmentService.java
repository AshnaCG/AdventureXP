package com.adventurealley.adventurexp.equipment;

import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

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


    public List<EquipmentOverview> getOverview() {
        // nøgle = "Gokart|Hjelm", værdi = [total, ledige, i stykker]
        Map<String, int []> counts = new LinkedHashMap<>();

        for (Equipment e : equipmentRepository.findAll()) {
            String activityName = e.getActivity() == null ? "Ingen aktivitet" : e.getActivity().getName();
            String key = activityName + "|" + e.getName();
            int[] c = counts.computeIfAbsent(key, k -> new int[3]);
            c[0]++;
            if (e.isAvailability() && e.getState() == State.OK) c[1]++;
            if (e.getState() == State.BROKEN) c[2]++;
        }

        List<EquipmentOverview> result = new ArrayList<>();
        counts.forEach((key, c) -> {
            String[] parts = key.split("\\|");
            result.add(new EquipmentOverview(parts[0], parts[1], c[0], c[1], c[2]));
        });
        return result;
    }
}
