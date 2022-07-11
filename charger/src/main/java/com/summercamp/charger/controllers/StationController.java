package com.summercamp.charger.controllers;

import com.summercamp.charger.dtos.StationDto;
import com.summercamp.charger.models.Station;
import com.summercamp.charger.services.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    StationService stationService;

    @GetMapping
    public List<Station> getStations() {
        return stationService.getStations();
    }

    @GetMapping(value = "/sort/{attribute}")
    public List<Station> getStationsSorted(@PathVariable("attribute") String attribute) {
        return stationService.getStationsSorted(attribute);
    } 

    @PostMapping
    public Station saveStation(@RequestBody StationDto stationDto){
        return stationService.saveStation(stationDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStation(@PathVariable("id") Long Id){
        stationService.deleteStation(Id);
    }

    @PatchMapping(value = "/{id}")
    public void updateStation(@PathVariable("id") Long Id, @RequestBody Station station){
        stationService.updateStation(Id, station);
    }

     
    @GetMapping(value = "/search/{name}")
    public Station getStationAfterName(@PathVariable("name") String name){
        return stationService.getStationAfterName(name);
    }

    @GetMapping(value = "/{id}")
    public Station getAfterId(@PathVariable("id") Long Id){
        
        System.out.println("ID = " + Id);
        
        return stationService.getStationAfterId(Id);
    }
    
    

}
