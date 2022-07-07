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

    public void updateLocation(Long Id, Location newLocation){
        Location myLocation = locationRepository.getById(Id); 
        myLocation = newLocation;
        locationRepository.save(myLocation);
    }
}
