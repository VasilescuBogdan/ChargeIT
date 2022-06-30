package com.summercamp.charger.models;

import lombok.*;
import javax.persistence.*;

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

    @Column(name = "StationTypeId", nullable = false)
    private Long stationTypeId;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "IsOpen", nullable = false)
    private Boolean isOpen;

    @Column(name = "Name", nullable = false)
    private Boolean name;

}
