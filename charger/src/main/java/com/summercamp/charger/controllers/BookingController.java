package com.summercamp.charger.controllers;

import com.summercamp.charger.dtos.BookingDto;
import com.summercamp.charger.models.Booking;
import com.summercamp.charger.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5500")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getBookings(){
        return bookingService.getBookings();
    }


    @PostMapping
    public Booking saveBooking(@RequestBody BookingDto bookingDto){
        
        return bookingService.saveBooking(bookingDto);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteBooking(@PathVariable("id") Long Id){
        bookingService.deleteBooking(Id);
    }

    @PatchMapping()
    public Booking updateBooking(@RequestBody BookingDto bookingDto){
        return bookingService.updateBooking(bookingDto);
    }

    @GetMapping(value = "/{id}")
    public Booking getAfterId(@PathVariable("id") Long Id){
        
        return bookingService.getBookingAfterId(Id);
    }

    @GetMapping(value = "/sort/{attribute}")
    public List<Booking> getBookingsSorted(@PathVariable("attribute") String attribute) {
        return bookingService.getBookingsSorted(attribute);
    } 

}
