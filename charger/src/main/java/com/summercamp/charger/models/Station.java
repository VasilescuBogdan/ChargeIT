package com.summercamp.charger.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Station {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "StationTypeId")
    private Long stationTypeId;

    @Column(name = "Address")
    private String address;

    @Column(name = "IsOpen")
    private Boolean isOpen;

    @Column(name = "Name")
    private Boolean name;

}
