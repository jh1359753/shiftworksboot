package org.shiftworksboot.repository;

import org.shiftworksboot.entity.ChatRoom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    List<ChatRoom> findChatRoomsByEmployee_EmpIdOrderByLastchatTimeDesc(String empId);
}
