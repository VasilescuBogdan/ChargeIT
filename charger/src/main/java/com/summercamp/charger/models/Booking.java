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

    @Column(name = "StartDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "LicenceCar", nullable = false)
    private String licenceCar;

    @ManyToOne()
    @JoinColumn(name = "station_id")
    private Station station;

}
