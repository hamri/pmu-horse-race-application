package com.pmu.race.db.jpa.repository;

import com.pmu.race.db.jpa.model.HorseRaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRaceRepository extends JpaRepository<HorseRaceEntity, Long> {
}
