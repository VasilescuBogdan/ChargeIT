package com.summercamp.charger.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
public class Station {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "IsOpen", nullable = false)
    private Boolean isOpen;

    @Column(name = "Name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "station_type_id")
    private StationType stationType;
}
