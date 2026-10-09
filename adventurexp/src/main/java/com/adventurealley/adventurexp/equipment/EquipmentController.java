package com.adventurealley.adventurexp.equipment;

import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(equipmentService.findById(id));
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Equipment> deleteById(@PathVariable("id")Long id){
        equipmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Equipment>update(@PathVariable ("id") Long id,
                                           @RequestBody Equipment equipment){
     equipmentService.update(id, equipment);
     return ResponseEntity.ok(equipment);
    }
}