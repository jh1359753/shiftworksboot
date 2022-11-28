package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.shiftworksboot.dto.BookingDto;

import javax.persistence.*;

@Entity
@Table(name = "rsc_booking")
@Getter @Setter
@ToString
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private int book_id;

    private String book_title;
    private String book_content;
    private String book_date;
    private String book_begin;
    private String meetingRoom;

    public static Booking createBooking(BookingDto bookingDto){
        Booking booking = new Booking();
        booking.setBook_title(bookingDto.getBook_title());
        booking.setBook_content(bookingDto.getBook_content());
        booking.setBook_date(bookingDto.getBook_date());
        booking.setBook_begin(bookingDto.getBook_begin());
        booking.setMeetingRoom(bookingDto.getMeetingRoom());

        return booking;
    }


}
