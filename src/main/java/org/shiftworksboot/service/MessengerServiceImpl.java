package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.entity.ChatRoom;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.repository.ChatRoomRepository;
import org.shiftworksboot.repository.EmployeeRepository;
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

    private final EmployeeRepository employeeRepository;
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

        return chatRoomList;
    }

    public ChatRoom getChatRoom(String roomId) {

        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);

        return chatRoom;
    }
    @Override
    public ChatRoom insertChatRoom(String empId, String chatroomName) {

        Employee findEmployee = employeeRepository.findByEmpId(empId);

        if(findEmployee == null){
            new EntityNotFoundException();
        }

        ChatRoom chatRoom = ChatRoom.createChatRoom(chatroomName, findEmployee);
        //ChatRoomRepository.save(chatRoom);

        return chatRoom;
    }
}
