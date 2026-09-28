package com.adventurealley.adventurexp.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findByNameIgnoreCase(String name);
}
