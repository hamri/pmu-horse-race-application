package com.pmu.race.manager.services;

import com.pmu.race.api.RaceApiDelegate;
import com.pmu.race.api.model.RaceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class RaceApiDelegateImpl implements RaceApiDelegate {

    @Override
    public ResponseEntity<RaceDto> addRace(@Validated RaceDto raceDto) {
        return RaceApiDelegate.super.addRace(raceDto);
    }
}
