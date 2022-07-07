package com.summercamp.charger.dtos;

import lombok.Data;

@Data
public class StationDto {

    private Long id;

    private Boolean isOpen;

    private String name;

    private Long stationTypeId;

    private Long locationId;
}
