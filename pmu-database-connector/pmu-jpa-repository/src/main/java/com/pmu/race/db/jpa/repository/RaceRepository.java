package com.pmu.race.db.jpa.repository;

import com.pmu.race.db.jpa.model.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, Long> {
}
