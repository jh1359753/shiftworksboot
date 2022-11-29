package org.shiftworksboot.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Log
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

//    @Test
//    @DisplayName("예약 테스트")
//    public void insertBookingTest(){
//        Booking booking = new Booking();
//        booking.setBook_id(1);
//        booking.setBook_title("예약 제목");
//        booking.setBook_content("예약 내용");
//        booking.setBook_date("2022-01-01");
//        booking.setBook_begin("12");
//        booking.setMeetingRoom("회의실1");
//
//        System.out.println(bookingRepository.save(booking));
//    }

//    @Test
//    @DisplayName("예약 조회 테스트")
//    public void getListBookingTest(){
//        List<Booking> bookingList = bookingRepository.findAll();
//        System.out.println(bookingList.toString());
//
//    }


    @Test
    @DisplayName("예약 자원 불러오기 테스트")
    public void getDupList(){
        List<Booking> dupBookingList = bookingRepository.findAllByBookDateAndMeetingRoom("2022-11-14", "MTR101");
        System.out.println(dupBookingList.toString());
    }


}