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

    @Column(name = "IsOpen", nullable = false)
    private Boolean isOpen;

    @Column(name = "Name", nullable = false, unique =  true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_type_id", nullable = false)
    private StationType stationType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}
