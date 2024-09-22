package com.pmu.race.kafka;

import com.pmu.race.avro.NewHorseRaceEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.function.Consumer;

@SpringBootTest(classes = {RaceManagerKafkaConfiguration.class, KafkaAutoConfiguration.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"},
        topics = {"${pmu.kafka.test.newRaceTopic}"})
@ActiveProfiles({"test"})
@Slf4j
public class HorseRaceEventProducerIT {

    @Autowired
    private RaceKafkaProducer<NewHorseRaceEvent> raceKafkaProducer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Value("${pmu.kafka.test.newRaceTopic}")
    private String newRaceTestTopic;

    @BeforeEach
    void setup() {
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());
        }
    }

    @Test
    public void testProduceNewHorseRaceEvent() {

        // Create new HorseRace Event
        final NewHorseRaceEvent newHorseRaceEvent = NewHorseRaceEvent.newBuilder()
                .setId(1L)
                .build();

        // Mock success process
        Runnable successProcess = null;

        // Mock Failure process
        Consumer<Throwable> failureProcess = null;

        // Send Event to topic
        raceKafkaProducer.produceEvent("1", newHorseRaceEvent, newRaceTestTopic, successProcess,
                failureProcess);
    }
}
