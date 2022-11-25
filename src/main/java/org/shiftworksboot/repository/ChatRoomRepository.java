package org.shiftworksboot.repository;

import org.shiftworksboot.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    //@Query("select chatroom from chatroom order by ")
}
