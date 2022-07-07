package com.summercamp.charger.controllers;

import com.summercamp.charger.models.StationType;
import com.summercamp.charger.services.StationTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5500")
@RestController
@RequestMapping("/api/stationTypes")
public class StationTypeController {

    @Autowired
    private StationTypeService stationTypeService;

    @GetMapping
    public List<StationType> getStationTypes(){
        return stationTypeService.getStationTypes();
    }

    @PostMapping
    public StationType saveStationType(@RequestBody StationType stationType){
        return stationTypeService.saveStationType(stationType);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStationType(@PathVariable("id") Long Id){
        stationTypeService.deleteStationType(Id);
    }

    @PatchMapping(value = "/{id}")
    public void updateStation(@PathVariable("id") Long Id, @RequestBody StationType stationType){
        stationTypeService.updateStationType(Id, stationType);
    }

}
