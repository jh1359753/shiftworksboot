package org.shiftworksboot.repository;

import org.shiftworksboot.dto.BookingSearchDto;
import org.shiftworksboot.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingRepositoryCustom {

    Page<Booking> getBookingPage(BookingSearchDto bookingSearchDto, Pageable pageable);
}
