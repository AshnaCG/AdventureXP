package com.adventurealley.adventurexp.equipment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    //Alt udstyr til én aktivitet - Spring laver SQL'en ud fra metodenavnet
    List<Equipment> findByActivityId(Long activityId);
}
