package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, String> {

    Chat findTopChatByRoomIdOrderBySendtimeDesc(String roomId);










//    SELECT *
//    FROM (select c.chat_id, c.content, c.sendtime, c.sender, cr.room_id, cr.room_name
//            FROM chatroom cr LEFT OUTER JOIN chat c
//                  ON  c.room_id = cr.room_id
//                  WHERE cr.room_id = #{room_id}
//    ORDER BY sendtime desc
//                        )
//    WHERE rownum <= 5
//    ORDER BY sendtime

    //@Query("select chat from Chat chat where chat.chatRoom.roomId = :roomId order by chat.sendtime desc ")
    List<Chat> findTop5ByRoomIdOrderBySendtimeAsc(String roomId);

//    @Query(value = "SELECT * " +
//                    "FROM (select c.chat_id, c.content, c.sendtime, c.sender, cr.room_id, cr.room_name " +
//                        "FROM chatroom cr LEFT OUTER JOIN chat c " +
//                            "ON  c.room_id = cr.room_id " +
//                        "WHERE cr.room_id = :roomId " +
//                        "ORDER BY sendtime desc " +
//                        ") " +
//                    "WHERE rownum <= 5 " +
//                    "ORDER BY sendtime", nativeQuery = true)
//    List<Chat> findChats(@Param("roomId") String roomId);
}
