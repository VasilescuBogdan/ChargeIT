package com.summercamp.charger.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "StationId", nullable = false)
    private Long stationId;

    @Column(name = "StartDateTime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "Duration", nullable = false)
    private Integer duration;

    @Column(name = "LicenceCar", nullable = false)
    private String licenceCar;
}
