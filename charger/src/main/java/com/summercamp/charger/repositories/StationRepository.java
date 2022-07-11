package com.summercamp.charger.repositories;

import com.summercamp.charger.models.Station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    
    public Station findStationByNameContaining(@Param("name") String name);
    
}
