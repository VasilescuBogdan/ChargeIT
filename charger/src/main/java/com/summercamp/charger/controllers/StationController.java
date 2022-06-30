package com.summercamp.charger.controllers;

import com.summercamp.charger.models.Station;
import com.summercamp.charger.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("API/stations")
public class StationController {

    @Autowired
    private StationRepository stationRepository;

    @GetMapping
    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    @PostMapping
    public Station saveStation(@RequestBody Station station){
        return stationRepository.save(station);
    }

    @DeleteMapping
    public void deleteStation(Station station){
        stationRepository.delete(station);
    }

    @GetMapping
    public Station getStationAfterId(Long Id) {return stationRepository.getById(Id);}
}
