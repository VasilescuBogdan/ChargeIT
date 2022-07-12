package com.summercamp.charger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "City", nullable = false)
    private String city;
    
    @Column(name = "Address", nullable = false, unique = true)
    private String address;

    @Column(name = "CoordX", nullable = false)
    private Float coordinateX;

    @Column(name = "CoordY", nullable = false)
    private Float coordinateY;
}
