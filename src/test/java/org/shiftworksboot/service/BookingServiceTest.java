package org.shiftworksboot.service;

import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Log
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Test
    @DisplayName("예약 서비스 테스트")
    public void insertBookingTest(){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBook_title("예약 서비스 제목");
        bookingDto.setBook_content("예약 서비스 내용");
        bookingDto.setBook_date("2023-01-01");
        bookingDto.setBook_begin("14");
        bookingDto.setMeetingRoom("회의실2");

        System.out.println(bookingService.insertBooking(bookingDto));
    }

}