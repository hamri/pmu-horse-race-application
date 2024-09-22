package com.pmu.race.db.jpa.repository;

import com.pmu.race.db.jpa.model.HorseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends JpaRepository<HorseEntity, Long> {
}
