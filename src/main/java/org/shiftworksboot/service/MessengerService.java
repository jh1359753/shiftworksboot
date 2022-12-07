package org.shiftworksboot.service;

import org.shiftworksboot.dto.ChatDto;
import org.shiftworksboot.entity.Chat;
import org.shiftworksboot.entity.ChatRoom;

import java.util.List;

public interface MessengerService {

    // 로그인된 계정의 채팅방 목록
    List<ChatRoom> getChatRoomList(String empId);
    ChatRoom getChatRoom(String roomId);

    ChatRoom insertChatRoom(String empId);

    List<Chat> getChatList(String roomUuid);

    void insertChat(ChatDto chatDto);
}
