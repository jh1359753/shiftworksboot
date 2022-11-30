package org.shiftworksboot.service;

import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.BookingDto;
import org.shiftworksboot.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Log
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

//    @Test
//    @DisplayName("예약 서비스 테스트")
//    public void insertBookingTest(){
//        BookingDto bookingDto = new BookingDto();
//        bookingDto.setBook_title("예약 서비스 제목");
//        bookingDto.setBook_content("예약 서비스 내용");
//        bookingDto.setBook_date("2023-01-01");
//        bookingDto.setBook_begin("14");
//        bookingDto.setMeetingRoom("회의실2");
//
//        System.out.println(bookingService.insertBooking(bookingDto));
//    }

//    @Test
//    @DisplayName("예약 조회 테스트")
//    public void getListBookingTest(){
//        List<Map<String, Object>> bookingList = bookingService.getBookingList();
//        System.out.println(bookingList.toString());
//
//    }

//    @Test
//    @DisplayName("예약 중복 테스트")
//    public void dupBookingTest(){
//        Booking booking = new Booking();
//        booking.setBookDate("2022-11-14");
//        booking.setMeetingRoom("MTR101");
//        booking.setBookBegin("11");
//        booking.setBookTitle("예약 중복 테스트");
//        booking.setBookContent("예약 중복 테스트 내용");
//
////        Booking testBooking = bookingService.insertBooking(booking);
//        Boolean testBooking = bookingService.insertBooking(booking);
//        System.out.println("예약 중복 테스트 결과........"+testBooking);
//    }

//    @Test
//    @DisplayName("예약자원 리스트 조회 테스트")
//    public void bookingListTest(){
//        List<Booking> bookingList = bookingService.bookingList();
//        System.out.println(bookingList.toString());
//    }

    @Test
    @DisplayName("예약 상세보기 테스트")
    public void getBookingTest(){
        Booking booking = bookingService.getBooking(3);

        System.out.println("결과값.................."+booking);
    }




}