package com.pmu.race.manager.services;

import com.pmu.race.api.RaceApiDelegate;
import com.pmu.race.api.model.HorseDto;
import com.pmu.race.api.model.RaceDto;
import com.pmu.race.avro.NewHorseRaceEvent;
import com.pmu.race.db.jpa.model.HorseEntity;
import com.pmu.race.db.jpa.model.HorseRaceEntity;
import com.pmu.race.db.jpa.model.RaceEntity;
import com.pmu.race.db.jpa.repository.RaceRepository;
import com.pmu.race.kafka.RaceKafkaProducer;
import jakarta.persistence.CascadeType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.stream.IntStreams;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RaceApiDelegateImpl implements RaceApiDelegate {

    private final RaceRepository raceRepository;
    private final RaceKafkaProducer<NewHorseRaceEvent> producer;

    @Override
    public ResponseEntity<RaceDto> addRace(@Validated RaceDto raceDto) {
        final List<@Valid HorseDto> horses = raceDto.getHorses();
        if (CollectionUtils.isEmpty(horses) || horses.size() < 3) {
            throw new IllegalArgumentException("At least 3 horses are needed to start a race");
        }

        final RaceEntity race = RaceEntity.builder()
                .name(raceDto.getName())
                .plannedAt(raceDto.getDate())
                .numberAtDay(raceDto.getNumberAtDay())
                .build();

        final List<HorseRaceEntity> horsesRace = IntStreams.range(horses.size()).mapToObj(rank -> {
            final HorseDto horseDto = horses.get(rank);
            final HorseEntity horseEntity = HorseEntity.builder()
                    .name(horseDto.getName())
                    .build();

            return HorseRaceEntity.builder()
                    .horse(horseEntity)
                    .numberInRace(rank)
                    .race(race)
                    .build();
        }).toList();
        race.setHorses(horsesRace);

        final RaceEntity createdRace = raceRepository.save(race);

        return RaceApiDelegate.super.addRace(raceDto);
    }
}
