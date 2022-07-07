package com.summercamp.charger.repositories;

import com.summercamp.charger.models.Station;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    /*
    @Query("SELECT * FROM Station s WHERE s.name = :name")
    public List<Station> findStationAfterName(@Param("name") String name);
    */
}
