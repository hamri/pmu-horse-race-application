package com.pmu.race.db.jpa.repository;

import com.pmu.race.db.jpa.model.HorseEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class HorseRepositoryTest {

    @Autowired
    private HorseRepository horseRepository;

    @Test
    public void testCreateNewHorse() {
        // Prepare test data
        final HorseEntity horse = HorseEntity.builder()
                .name("H1")
                .build();

        // Test
        final HorseEntity horseEntity = horseRepository.save(horse);

        // Assertions
        Assertions.assertThat(horseEntity).isNotNull();
    }
}
