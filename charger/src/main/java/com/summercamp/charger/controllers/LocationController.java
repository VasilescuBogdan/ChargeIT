package com.summercamp.charger.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.summercamp.charger.models.Location;
import com.summercamp.charger.services.LocationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://localhost:5500")
@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    @Autowired
    LocationService locationService;

    @GetMapping
    public List<Location> getStationTypes(){
        return locationService.getStationTypes();
    }

    @PostMapping
    public Location saveStationType(@RequestBody Location location){
        return locationService.saveStationType(location);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteLocation(@PathVariable("id") Long Id){
        locationService.deleteLocation(Id);
    }

    @PatchMapping(value = "/{id}")
    public void updateLocation(@PathVariable("id") Long Id, @RequestBody Location location){
        locationService.updateLocation(Id, location);
    }

}
