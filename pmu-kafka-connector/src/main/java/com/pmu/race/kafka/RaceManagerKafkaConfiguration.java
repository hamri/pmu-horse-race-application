package com.pmu.race.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.pmu.race.kafka")
@EnableKafka
public class RaceManagerKafkaConfiguration {
  @Bean
  public <T> ProducerFactory<String, T> producerFactory(final KafkaProperties kafkaProperties) {
    final Map<String, Object> builtProperties = kafkaProperties.buildProducerProperties(null);
    return new DefaultKafkaProducerFactory<>(builtProperties);
  }

  @Bean
  public <T> KafkaTemplate<String, T> kafkaTemplate(final ProducerFactory<String, T> factory) {
    return new KafkaTemplate<>(factory);
  }
}
