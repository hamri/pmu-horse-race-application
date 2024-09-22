package com.pmu.race.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class RaceKafkaProducer<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    public RaceKafkaProducer(final KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceEvent(final String eventKey, final T event, final String topicName,
                             Runnable onSuccess, Consumer<Throwable> onFailed) {

        kafkaTemplate.send(topicName, eventKey, event).whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                onFailed.accept(throwable);
            } else {
                onSuccess.run();
            }
        });

    }
}
