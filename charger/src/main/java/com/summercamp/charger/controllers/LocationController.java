package com.summercamp.charger.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.summercamp.charger.models.Location;
import com.summercamp.charger.repositories.LocationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> getStationTypes(){
        return locationRepository.findAll();
    }

    @PostMapping
    public Location saveStationType(@RequestBody Location location){
        return locationRepository.save(location);
    }

    @DeleteMapping
    public void deleteStationType(@RequestBody Location location){locationRepository.delete(location);}

    @GetMapping(value = "/{id}")
    public Location getBookingAfterId(@PathVariable("id") Long Id) {return locationRepository.findById(Id).get();}

    @PutMapping
    public Location updateStationType(@RequestBody Location location) {return locationRepository.save(location);}

}
