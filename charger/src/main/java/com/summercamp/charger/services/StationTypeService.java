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

    public void updateStationType(Long Id, StationType newStationType){
        StationType myStationType = stationTypeRepository.getById(Id); 
        myStationType = newStationType;
        stationTypeRepository.save(myStationType);
    }

}
