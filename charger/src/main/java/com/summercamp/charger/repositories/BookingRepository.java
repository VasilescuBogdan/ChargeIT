package com.summercamp.charger.repositories;

import com.summercamp.charger.models.Booking;
import com.summercamp.charger.models.Station;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByEndDateAfterAndStartDateBeforeAndStation(LocalDateTime startDateTime, LocalDateTime endDateTime, Station station);
    
}
