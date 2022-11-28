package org.shiftworksboot.repository;

import org.shiftworksboot.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    @Query(value = "select c from ChatRoom c where c.employee.emp_id = :emp_id ")
    List<ChatRoom> findChatRooms(@Param("emp_id") String emp_id);
}
