package com.summercamp.charger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.summercamp.charger.dtos.BookingDto;
import com.summercamp.charger.models.Booking;
import com.summercamp.charger.models.Station;
import com.summercamp.charger.repositories.BookingRepository;
import com.summercamp.charger.repositories.StationRepository;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StationRepository stationRepository;
    
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    public Booking saveBooking(BookingDto bookingDto){
        
        Station station = stationRepository.findById(bookingDto.getStationId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving stationDpo type "));

        Booking booking = new Booking();
        booking.setStation(station);
        booking.setStartDate(bookingDto.getStartDateTime());
        booking.setEndDate(bookingDto.getStartDateTime().plusMinutes(bookingDto.getDuration()));
        booking.setLicenceCar(bookingDto.getLicenceCar());
        
        if(bookingRepository.findByEndDateAfterAndStartDateBeforeAndStation(booking.getStartDate(), booking.getEndDate(), station).size() > 0){
            throw new RuntimeException("Error interval is overlapping.");
        }; 
        
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long Id){
        bookingRepository.delete(bookingRepository.getById(Id));
    }

    public Booking updateBooking(BookingDto bookingDto){
               
        Booking booking = bookingRepository.findById(bookingDto.getId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving bookingDto type ")); 

        Station station = stationRepository.findById(bookingDto.getStationId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving bookingDto type."));
        
        booking.setStation(station);
        booking.setStartDate(bookingDto.getStartDateTime());
        booking.setEndDate(bookingDto.getStartDateTime().plusMinutes(bookingDto.getDuration()));
        booking.setLicenceCar(bookingDto.getLicenceCar());

        if(bookingRepository.findByEndDateAfterAndStartDateBeforeAndStation(booking.getStartDate(), booking.getEndDate(), station).size() > 0){
            throw new RuntimeException("Error interval is overlapping.");
        }; 

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsSorted(String attribute) {
        return bookingRepository.findAll(Sort.by(Sort.Direction.ASC, attribute));
    } 

    public Booking getBookingAfterId(Long Id){   
        return bookingRepository.findById(Id).orElseThrow(() -> new RuntimeException("There is no such Id")); 
    }

}
