package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByBookDateAndMeetingRoom(String bookDate, String meetingRoom);




}
