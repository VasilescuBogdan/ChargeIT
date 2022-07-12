package com.summercamp.charger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summercamp.charger.repositories.LocationRepository;
import com.summercamp.charger.models.Location;

import java.util.List;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getStationTypes(){
        return locationRepository.findAll();
    }

    public Location saveStationType(Location location){
        return locationRepository.save(location);
    }

    public void deleteLocation(Long Id){
        locationRepository.delete(locationRepository.getById(Id));
    }

    public Location updateLocation(Location location){
        
        Location newLocation = locationRepository.findById(location.getId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving location type "));
        
        newLocation.setAddress(location.getAddress());
        newLocation.setCity(location.getCity());
        newLocation.setCoordinateX(location.getCoordinateX());
        newLocation.setCoordinateY(location.getCoordinateY());

        return locationRepository.save(newLocation);
    }

    public Location getLocationAfterId(Long Id){
        
        return locationRepository.findById(Id).orElseThrow(() -> new RuntimeException("There is no such Id")); 
    }

}
