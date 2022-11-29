package org.shiftworksboot.controller;

import org.shiftworksboot.dto.BookingDto;
import org.shiftworksboot.entity.Booking;
import org.shiftworksboot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/booking/*")
public class BookingController {

    @Autowired
    private BookingService service;

    @GetMapping("/new")
    public ModelAndView insertBookingForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("booking/BOK_insertForm");

        return mav;
    }

    @PostMapping("/new")
    public ResponseEntity<String> insertBooking(@RequestBody BookingDto bookingDto){
        Booking booking = Booking.createBooking(bookingDto);
        Booking savedBooking = service.insertBooking(booking);
        System.out.println("BookingController 결과값.............:"+savedBooking);


        return savedBooking != null
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.OK);
    }
}
