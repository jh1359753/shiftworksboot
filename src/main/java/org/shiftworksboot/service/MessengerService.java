package org.shiftworksboot.service;

import org.shiftworksboot.entity.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessengerService {

    // 로그인된 계정의 채팅방 목록
    List<ChatRoom> getChatRoomList(String empId);
    ChatRoom getChatRoom(String roomId);

}
