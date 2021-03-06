package com.summercamp.charger.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingDto {
    
    private Long id;

    private LocalDateTime startDate;
    
    private Integer duration;

    private String licenceCar;

    private Long stationId;
}
