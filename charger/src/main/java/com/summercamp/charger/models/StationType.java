package com.summercamp.charger.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
public class StationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Power", nullable = false)
    private Integer power;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "PlugType", nullable = false)
    private String plugType;


}
