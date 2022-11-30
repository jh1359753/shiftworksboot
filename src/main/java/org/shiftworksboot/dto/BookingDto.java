package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Booking;

import javax.persistence.GeneratedValue;

@Getter @Setter
public class BookingDto {

    private String bookTitle;
    private String bookContent;
    private String bookDate;
    private String bookBegin;
    private String meetingRoom;

    private static ModelMapper modelMapper;

//    public Booking createBooking(){
//        return modelMapper.map(this, Booking.class);
//    }

    public static BookingDto of(Booking booking){

        return modelMapper.map(booking, BookingDto.class);
    }
}
