package org.shiftworksboot.controller;

import org.shiftworksboot.dto.BookingDto;
import org.shiftworksboot.dto.BookingSearchDto;
import org.shiftworksboot.entity.Booking;
import org.shiftworksboot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
//        Booking savedBooking = service.insertBooking(booking);
        System.out.println("변환된 booking................"+booking);
        Boolean savedBooking = service.insertBooking(booking);
        System.out.println("BookingController 결과값.............:"+savedBooking);


        return savedBooking == true
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.OK);
    }


    @GetMapping("/main")
    public ModelAndView bookingMain(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("booking/BOK_mainCal");

        return mav;
    }

    @GetMapping("/calendar")
    public List<Map<String, Object>> bookingCalendar(){
        System.out.println("예약된 리스트 확인....."+service.getBookingList().toString());

        return service.getBookingList();
    }


    @GetMapping("/list")
    public ModelAndView bookingList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("booking/BOK_list");
        mav.addObject("event", service.bookingList());

        return mav;
    }

    @GetMapping(value = {"/list/{page}"})
    public ModelAndView bookingPaging(BookingSearchDto bookingSearchDto, @PathVariable("page")Optional<Integer>page){
        ModelAndView mav = new ModelAndView();
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get(): 0, 3);
        Page<Booking> paging =
                service.getBookingPage(bookingSearchDto, pageable);
        mav.addObject("event", paging);
        mav.addObject("bookingSearchDto", bookingSearchDto);
        mav.addObject("maxPage", 5);
        mav.setViewName("booking/BOK_list");

        return mav;
    }

    @GetMapping(value = "/{bookId}")
    public ModelAndView getBooking(@PathVariable("bookId") int bookId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/booking/BOK_detail");
//        Booking booking = service.getBooking(bookId);
//        System.out.println("가져온 예약................."+booking);
//        BookingDto dto = BookingDto.of(booking);
//        System.out.println("변환된 dto객체.............."+dto);

        mav.addObject("event", service.getBooking(bookId));
//        mav.addObject("event", service.getBooking(bookId));

        return mav;
    }

}
