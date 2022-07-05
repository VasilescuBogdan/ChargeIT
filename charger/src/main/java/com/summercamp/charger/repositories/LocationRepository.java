package com.summercamp.charger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.summercamp.charger.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
    
}
