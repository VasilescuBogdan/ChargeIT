package com.summercamp.charger.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.summercamp.charger.models.Location;
import com.summercamp.charger.services.LocationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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

    @PatchMapping()
    public Location updateLocation(@RequestBody Location location){
        return locationService.updateLocation(location);
    }

    @GetMapping(value = "/{id}")
    public Location getLocationAfterId(@PathVariable("id") Long Id){
        return locationService.getLocationAfterId(Id);
    }

}
