<<<<<<<< HEAD:adventurexp/src/main/java/adventure/adventurexp/controller/EquipmentController.java
package adventure.adventurexp.controller;
========
package com.adventurealley.adventurexp.equipment;
>>>>>>>> 135b39884ccbec596f97b07f7a57b21c3f91463f:adventurexp/src/main/java/com/adventurealley/adventurexp/equipment/EquipmentController.java

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import adventure.adventurexp.model.Equipment;
import adventure.adventurexp.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/adventure/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/overview")
    public ResponseEntity<List<EquipmentOverviewDTO>> getOverview() {
        return ResponseEntity.ok(equipmentService.getOverview());
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAll());
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        return ResponseEntity.ok(equipmentService.create(equipment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(equipmentService.findById(id));
    }
}