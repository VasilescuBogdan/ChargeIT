package com.summercamp.charger.controllers;

import com.summercamp.charger.models.Booking;
import com.summercamp.charger.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("API/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking saveBooking(@RequestBody Booking booking){
        return bookingRepository.save(booking);
    }

    @DeleteMapping
    public void deleteBooking(Booking booking){
        bookingRepository.delete(booking);
    }

    @GetMapping
    public Booking getBookingAfterId(Long Id) {return bookingRepository.getById(Id);}
}
