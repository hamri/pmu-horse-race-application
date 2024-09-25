package com.pmu.race.manager;

import com.pmu.race.db.jpa.model.RaceManagerEntitiesConfiguration;
import com.pmu.race.db.jpa.repository.RaceManagerRepositoryConfiguration;
import com.pmu.race.kafka.RaceManagerKafkaConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RaceManagerRepositoryConfiguration.class, RaceManagerKafkaConfiguration.class})
public class HorseRaceManagerConfiguration {
}
