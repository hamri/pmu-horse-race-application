package com.pmu.race.db.jpa.model;

import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Builder
@Entity
@Table(name = "T_PMU_HORSE_RACE", uniqueConstraints = {
        @UniqueConstraint(name = "uc_horse_race", columnNames = {"horse_id", "race_id"})
})
public class HorseRaceEntity {

    @Id
    @GeneratedValue(generator = "horse_race_sequence_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "horse_race_sequence_generator", sequenceName = "horse_race_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Integer numberInRace;

    @ManyToOne
    @JoinColumn(name="horse_id", nullable=false, foreignKey = @ForeignKey(name = "fk__t_pmu_horse_race__horse"))
    private HorseEntity horse;

    @ManyToOne
    @JoinColumn(name="race_id", nullable=false, foreignKey = @ForeignKey(name = "fk__t_pmu_horse_race__race"))
    private RaceEntity race;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false, updatable = false)
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updateDate;
}
