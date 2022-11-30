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
    @Column(name = "bookId")
    private int bookId;

    private String bookTitle;
    private String bookContent;
    private String bookDate;
    private String bookBegin;
    private String meetingRoom;

    public static Booking createBooking(BookingDto bookingDto){
        Booking booking = new Booking();
        booking.setBookTitle(bookingDto.getBookTitle());
        booking.setBookContent(bookingDto.getBookContent());
        booking.setBookDate(bookingDto.getBookDate());
        booking.setBookBegin(bookingDto.getBookBegin());
        booking.setMeetingRoom(bookingDto.getMeetingRoom());

        return booking;
    }


}
