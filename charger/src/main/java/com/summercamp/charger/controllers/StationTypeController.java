package com.summercamp.charger.controllers;

import com.summercamp.charger.models.StationType;
import com.summercamp.charger.repositories.StationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5500")
@RestController
@RequestMapping("/api/stationTypes")
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
    public void deleteStationType(@RequestBody StationType stationType){stationTypeRepository.delete(stationType);}

    @GetMapping(value = "/{id}")
    public StationType getBookingAfterId(@PathVariable("id") Long Id) {return stationTypeRepository.findById(Id).get();}

    @PutMapping
    public StationType updateStationType(@RequestBody StationType stationType) {return stationTypeRepository.save(stationType);}

}
