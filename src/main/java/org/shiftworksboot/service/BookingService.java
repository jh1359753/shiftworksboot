package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.BookingDto;
import org.shiftworksboot.entity.Booking;
import org.shiftworksboot.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;


    @Transactional
    public Booking insertBooking(Booking booking){
//        Booking booking = Booking.createBooking(bookingDto);
        Booking savedBooking = bookingRepository.save(booking);

//        Booking booking = bookingDto.createBooking();

        return savedBooking;
    }

}
