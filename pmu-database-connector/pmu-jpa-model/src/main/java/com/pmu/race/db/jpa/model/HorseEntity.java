package com.pmu.race.db.jpa.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@Table(name = "T_PMU_HORSE", uniqueConstraints = {
        @UniqueConstraint(name = "uc_horse_name", columnNames = { "name" })
})
public class HorseEntity {

    @Id
    @GeneratedValue(generator = "horse_sequence_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "horse_sequence_generator", sequenceName = "horse_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false, updatable = false)
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updateDate;

}
