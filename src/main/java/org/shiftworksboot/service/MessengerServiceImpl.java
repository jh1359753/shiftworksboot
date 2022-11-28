package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.entity.Chat;
import org.shiftworksboot.entity.ChatRoom;
import org.shiftworksboot.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessengerServiceImpl implements MessengerService{


    private final ChatRoomRepository chatRoomRepository;

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    // 의존 관계 주입 완료되면 실행되는 코드
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    @Transactional
    @Override
    public List<ChatRoom> getChatRoomList(String empId) {
        // 채팅방 불러오기
        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRooms(empId);

        if (chatRoomList == null || chatRoomList.isEmpty()) {
            // 찾지 못할 경우 예외 처리
            throw new EntityNotFoundException();
        }

        return chatRoomList;
    }

    public ChatRoom getChatRoom(String roomId) {

        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);

        return chatRoom;
    }
}
