package com.pmu.race.db.jpa.model;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Entity
@Table(name = "T_PMU_RACE", uniqueConstraints = {
        @UniqueConstraint(name = "uc_race_name_number_at_day_planned_at", columnNames = {"name", "numberAtDay", "plannedAt"})
})
public class RaceEntity {

    @Id
    @GeneratedValue(generator = "race_sequence_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "race_sequence_generator", sequenceName = "race_sequence", allocationSize = 1)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer numberAtDay;

    @Column(nullable = false)
    private OffsetDateTime plannedAt;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false, updatable = false)
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updateDate;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorseRaceEntity> horses;
}
