package com.pmu.race.kafka;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.function.Consumer;

@SpringBootTest(classes = {KafkaConsumerConfiguration.class, KafkaProducerConfiguration.class,
  KafkaAutoConfiguration.class, ConversionResultEventProducerAndConsumerIT.ConversionResultEventTestConfig.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"},
  topics = {"${kemm.kafka.test.conversionResultTopic}"})
@ActiveProfiles({"test"})
@Slf4j
public class HorseRaceEventProducerIT {

  @BeforeEach
  void setup() {
    for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
      ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());
    }
  }
}
