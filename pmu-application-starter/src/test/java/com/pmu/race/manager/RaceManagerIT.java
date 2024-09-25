package com.pmu.race.manager;

import com.pmu.race.api.model.HorseDto;
import com.pmu.race.api.model.RaceDto;
import com.pmu.race.manager.services.RaceApiDelegateImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;
import java.util.List;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"},
        topics = {"${pmu.kafka.raceTopic}"})
@ActiveProfiles("test")
public class RaceManagerIT {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Value("${pmu.kafka.raceTopic}")
    private String newRaceTestTopic;

    @Autowired
    private RaceApiDelegateImpl raceApiDelegate;

    @BeforeEach
    void setup() {
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());
        }
    }

    @Test
    public void testAddNewRace() throws Exception {
        // Prepare Race to create
        final HorseDto horseDto1 = new HorseDto();
        horseDto1.setName("H1");

        final HorseDto horseDto2 = new HorseDto();
        horseDto2.setName("H2");

        final HorseDto horseDto3 = new HorseDto();
        horseDto3.setName("H3");

        final RaceDto raceDtoToCreate = new RaceDto();
        raceDtoToCreate.setName("R1");
        raceDtoToCreate.setDate(OffsetDateTime.now());
        raceDtoToCreate.setNumberAtDay(1);
        raceDtoToCreate.setHorses(List.of(horseDto1, horseDto2, horseDto3));

        // Test
        final ResponseEntity<RaceDto> raceDtoResponseEntity = raceApiDelegate.addRace(raceDtoToCreate);
        final RaceDto createdRace = raceDtoResponseEntity.getBody();

        // Assertions
    }
}
