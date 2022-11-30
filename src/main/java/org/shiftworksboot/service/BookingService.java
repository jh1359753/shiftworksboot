package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.BookingDto;
import org.shiftworksboot.dto.BookingSearchDto;
import org.shiftworksboot.entity.Booking;
import org.shiftworksboot.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;


    //자원예약(+중복 검증)
    @Transactional
    public Boolean insertBooking(Booking booking) {

        List<Booking> checkingList = bookingRepository.findAllByBookDateAndMeetingRoom(booking.getBookDate(), booking.getMeetingRoom());
//        System.out.println(checkingList.toString());
//        System.out.println(booking.toString());

        Boolean isAble = true;
        for (int i = 0; i < checkingList.size(); i++) {
            if (checkingList.get(i).getBookBegin().equals(booking.getBookBegin())) {
                isAble = false;
            }
        }
//        System.out.println("isAble의 상태는............."+isAble);

        Boolean result = false;
        if (isAble == true) {
            bookingRepository.save(booking);
            result = true;
        } else if (isAble != true) {
            System.out.println("예약 중복!!!");
            result = false;
        }

        return result;
    }



    //FullCal 출력용 리스트
    @Transactional
    public List<Map<String, Object>> getBookingList(){
        List<Booking> allBookingList = bookingRepository.findAll();

        List<Map<String,Object>> bookingList = new ArrayList<Map<String,Object>>();
        for (int i=0;i<allBookingList.size();i++){
            Map<String,Object> map = new HashMap<>();

            String begin = allBookingList.get(i).getBookBegin();
            int endTime = Integer.parseInt(allBookingList.get(i).getBookBegin())+2;
            String end = Integer.toString(endTime);

            map.put("title", allBookingList.get(i).getBookTitle());
            map.put("start", allBookingList.get(i).getBookDate().substring(0,10)+"T"+begin+":00");
            map.put("end", allBookingList.get(i).getBookDate().substring(0,10)+"T"+end+":00");

            if(allBookingList.get(i).getMeetingRoom().equals("MTR101")) {
                map.put("color", "#ACA1A4");
            } else if (allBookingList.get(i).getMeetingRoom().equals("MTR202")) {
                map.put("color", "#1EAF62");
            }else {
                map.put("color", "#1C3359");
            }
            bookingList.add(i, map);
        }

        return bookingList;
    }


    @Transactional
    public List<Booking> bookingList(){

        return bookingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Booking> getBookingPage(BookingSearchDto bookingSearchDto, Pageable pageable){
        return bookingRepository.getBookingPage(bookingSearchDto, pageable);
    }


    @Transactional
    public Booking getBooking(int bookId){

        return bookingRepository.findAllByBookId(bookId);
    }


}
