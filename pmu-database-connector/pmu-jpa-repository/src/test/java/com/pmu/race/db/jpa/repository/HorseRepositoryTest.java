package com.pmu.race.db.jpa.repository;

import com.pmu.race.db.jpa.model.HorseEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

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
        final HorseEntity horseEntity = horseRepository.saveAndFlush(horse);

        // Assertions
        Assertions.assertThat(horseEntity).isNotNull();
        Assertions.assertThat(horseEntity.getName()).isEqualTo("H1");
    }

    @Test
    public void testCreateTwoHorses_with_the_same_name() {
        // Prepare test data
        final HorseEntity firstHorse = HorseEntity.builder()
                .name("H2")
                .build();

        final HorseEntity secondHorse = HorseEntity.builder()
                .name("H2")
                .build();

        // Test and assertions
        Assertions.assertThatThrownBy(() -> horseRepository.saveAllAndFlush(Arrays.asList(firstHorse, secondHorse)))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
