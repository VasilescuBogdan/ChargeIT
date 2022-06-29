package com.summercamp.charger.controllers;

import com.summercamp.charger.models.StationType;
import com.summercamp.charger.repositories.StationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("API/stationTypes")
public class StationTypeController {

    @Autowired
    private StationTypeRepository stationTypeRepository;

    @GetMapping
    public List<StationType> getStationTypes(){
        return stationTypeRepository.findAll();
    }

    @PostMapping
    public StationType saveStationType(@RequestBody StationType stationType){
        return stationTypeRepository.save(stationType);
    }

    @DeleteMapping
    public void deleteStationType(StationType stationType){
        stationTypeRepository.delete(stationType);
    }
}
