package org.shiftworksboot.repository;

import org.shiftworksboot.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    List<ChatRoom> findChatRoomsByRoomId(String roomId);

    List<ChatRoom> findChatRoomByEmployee_EmpId(String empId);
}
