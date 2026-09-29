package com.adventurealley.adventurexp.equipment;

public record EquipmentOverviewDTO(
        String activity,
        String equipment,
        int total,
        int available,
        int broken)
{}
