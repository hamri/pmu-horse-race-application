package com.pmu.race.db.jpa.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.pmu.race.db.jpa.model")
public class RaceManagerEntitiesConfiguration {
}
