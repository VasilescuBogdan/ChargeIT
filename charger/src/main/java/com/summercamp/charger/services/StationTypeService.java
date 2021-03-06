package com.summercamp.charger.services;

import com.summercamp.charger.models.StationType;
import com.summercamp.charger.repositories.StationTypeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationTypeService {

    @Autowired
    private StationTypeRepository stationTypeRepository;

    public List<StationType> getStationTypes(){
        return stationTypeRepository.findAll();
    }
    
    public StationType saveStationType(StationType stationType){
        return stationTypeRepository.save(stationType);
    }

    public void deleteStationType(Long Id){
        stationTypeRepository.delete(stationTypeRepository.getById(Id));
    }

    public StationType updateStationType(StationType stationType){

        StationType newStationType = stationTypeRepository.findById(stationType.getId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving stationType type "));
        
        newStationType.setName(stationType.getName());
        newStationType.setPlugType(stationType.getPlugType());
        newStationType.setPower(stationType.getPower());

        return stationTypeRepository.save(newStationType);
    }

    public StationType getStationTypeAfterId(Long Id){
        
        return stationTypeRepository.findById(Id).orElseThrow(() -> new RuntimeException("There is no such Id")); 
    }

}
