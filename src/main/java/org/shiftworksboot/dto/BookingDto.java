package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Booking;

import javax.persistence.GeneratedValue;

@Getter @Setter
public class BookingDto {

    private String book_title;
    private String book_content;
    private String book_date;
    private String book_begin;
    private String meetingRoom;

    private static ModelMapper modelMapper;

//    public Booking createBooking(){
//        return modelMapper.map(this, Booking.class);
//    }

    public static BookingDto of(Booking booking){
        return modelMapper.map(booking, BookingDto.class);
    }
}
