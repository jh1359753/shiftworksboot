package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer>,
        QuerydslPredicateExecutor<Booking>, BookingRepositoryCustom {

    List<Booking> findAllByBookDateAndMeetingRoom(String bookDate, String meetingRoom);

    Booking findAllByBookId(Integer bookId);


}
