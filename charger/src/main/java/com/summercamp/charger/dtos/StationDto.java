package com.summercamp.charger.dtos;

import com.summercamp.charger.models.StationType;
import lombok.Data;

@Data
public class StationDto {

    private Long id;

    private String address;

    private Boolean isOpen;

    private String name;

    private Long stationTypeId;
}
