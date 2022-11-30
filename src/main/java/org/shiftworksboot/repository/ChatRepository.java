package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, String> {

    Chat findTopChatByRoomIdOrderBySendtimeDesc(String roomId);

    List<Chat> findTop5ByRoomIdOrderBySendtimeAsc(String roomId);

}
