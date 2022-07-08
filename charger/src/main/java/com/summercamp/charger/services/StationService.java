package com.summercamp.charger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.summercamp.charger.dtos.StationDto;
import com.summercamp.charger.models.Location;
import com.summercamp.charger.models.Station;
import com.summercamp.charger.models.StationType;
import com.summercamp.charger.repositories.LocationRepository;
import com.summercamp.charger.repositories.StationRepository;
import com.summercamp.charger.repositories.StationTypeRepository;

@Service
public class StationService {
    
    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationTypeRepository stationTypeRepository;

    @Autowired
    private LocationRepository locationRepository;


    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    public Station saveStation(StationDto stationDto){


        StationType stationType = stationTypeRepository.findById(stationDto.getStationTypeId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type "));

        Location location = locationRepository.findById(stationDto.getLocationId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type "));

        Station station = new Station();
        station.setStationType(stationType);
        station.setLocation(location);
        station.setName(stationDto.getName());
        station.setIsOpen(stationDto.getIsOpen());

        return stationRepository.save(station);
    }

    public void deleteStation(Long Id){
        stationRepository.delete(stationRepository.getById(Id));
    }

    public void updateStation(Long Id, Station newStation){
        Station myStation = stationRepository.getById(Id); 
        myStation = newStation;
        stationRepository.save(myStation);
    }

    public List<Station> getStationsSorted(String attribute) {
        return stationRepository.findAll(Sort.by(Sort.Direction.ASC, attribute));
    } 

    
    public List<Station> getStationAfterName(String name){
        return stationRepository.findStationByName(name);
    }
    
}
