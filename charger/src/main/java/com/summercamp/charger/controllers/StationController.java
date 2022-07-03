package com.summercamp.charger.controllers;

import com.summercamp.charger.dtos.StationDto;
import com.summercamp.charger.models.Station;
import com.summercamp.charger.models.StationType;
import com.summercamp.charger.repositories.StationRepository;
import com.summercamp.charger.repositories.StationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationTypeRepository stationTypeRepository;

    @GetMapping
    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    @PostMapping
    public Station saveStation(@RequestBody StationDto stationDto){


        StationType stationType = stationTypeRepository.findById(stationDto.getStationTypeId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving stationDpo type "));

        Station station = new Station();
        station.setStationType(stationType);
        station.setName(stationDto.getName());
        station.setAddress(stationDto.getAddress());
        station.setIsOpen(stationDto.getIsOpen());

        return stationRepository.save(station);
    }

    @DeleteMapping
    public void deleteStation(@RequestBody Station station){stationRepository.delete(station);}

    @GetMapping(value = "/{id}")
    public Station getStationAfterId(@PathVariable("id") Long Id) {return stationRepository.findById(Id).get();}
}
