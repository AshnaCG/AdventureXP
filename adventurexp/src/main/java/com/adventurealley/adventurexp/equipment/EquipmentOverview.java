package com.adventurealley.adventurexp.equipment;

public record EquipmentOverview(
        String activity,
        String equipment,
        int total,
        int available,
        int broken)
{}
