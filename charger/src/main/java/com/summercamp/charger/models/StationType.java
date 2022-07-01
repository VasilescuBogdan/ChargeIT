package com.summercamp.charger.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Power", nullable = false)
    private Integer power;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "PlugType", nullable = false)
    private String plugType;


}
