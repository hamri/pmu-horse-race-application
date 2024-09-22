package com.pmu.race.db.jpa.repository;

import com.pmu.race.db.jpa.model.RaceManagerEntitiesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import({RaceManagerEntitiesConfiguration.class})
@EnableJpaRepositories(value = "com.pmu.race.db.jpa.repository")
public class RaceManagerRepositoryConfiguration {
}
