package com.summercamp.charger.models;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "StartDateTime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "Duration", nullable = false)
    private Integer duration;

    @Column(name = "LicenceCar", nullable = false)
    private String licenceCar;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

}
