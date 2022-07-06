package com.summercamp.charger.controllers;

import com.summercamp.charger.dtos.BookingDto;
import com.summercamp.charger.models.Booking;
import com.summercamp.charger.models.Station;
import com.summercamp.charger.repositories.BookingRepository;
import com.summercamp.charger.repositories.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5500")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking saveBooking(@RequestBody BookingDto bookingDto){
        
        Station station = stationRepository.findById(bookingDto.getStationId())
            .orElseThrow(() -> new RuntimeException("Error while retrieving stationDpo type "));

        Booking booking = new Booking();
        booking.setStation(station);
        booking.setStartDateTime(bookingDto.getStartDateTime());
        booking.setDuration(bookingDto.getDuration());
        booking.setLicenceCar(bookingDto.getLicenceCar()); 
        
        return bookingRepository.save(booking);
    }


    @DeleteMapping()
    public void deleteBooking(@RequestBody Booking booking) {bookingRepository.delete(booking);}

    @GetMapping(value = "/{id}")
    public Booking getBookingAfterId(@PathVariable("id") Long Id) {return bookingRepository.findById(Id).get();}

}
