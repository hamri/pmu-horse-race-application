package com.pmu.race.db.jpa.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

@Import(RaceManagerRepositoryConfiguration.class)
@SpringBootConfiguration
public class RepositoryTestConfiguration {
}
