package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shiftworksboot.dto.ChatDto;
import org.shiftworksboot.entity.Chat;
import org.shiftworksboot.entity.ChatRoom;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.repository.ChatRepository;
import org.shiftworksboot.repository.ChatRoomRepository;
import org.shiftworksboot.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessengerServiceImpl implements MessengerService{

    private final EmployeeRepository employeeRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;


    @Transactional
    @Override
    public List<ChatRoom> getChatRoomList(String empId) {

        // 로그인된 계정의 채팅방 가져오기
        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomByEmployee_EmpId(empId);

       /* // 뷰에 전달할 채팅방 정보 리스트
        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();

        // entity -> dto 변환
        for (ChatRoom chatRoom : chatRoomList) {

            //로그인된 계정의 각 채팅방에서 가장 최근 채팅 정보 가져오기
            Chat chat = chatRepository.findTopChatByRoomIdOrderBySendtimeDesc(chatRoom.getRoomId());

            ChatRoomDto chatRoomDto = new ChatRoomDto();
            chatRoomDto.of(chatRoom);
            chatRoomDtoList.add(chatRoomDto);

            log.info("service : chatRoomList : " + chatRoomList.get(0).getRoomName());
            log.info("service : chatRoomDto : " + chatRoomDto.getRoomName());
        }*/

        return chatRoomList;
    }

    public ChatRoom getChatRoom(String roomId) {

        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);

        return chatRoom;
    }

    @Override
    public ChatRoom insertChatRoom(String empId) {

        Employee findEmployee = employeeRepository.findByEmpId(empId);

        if(findEmployee == null){
            new EntityNotFoundException();
        }

        ChatRoom chatRoom = ChatRoom.createChatRoom(findEmployee);
        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    @Override
    public List<Chat> getChatList(String roomId) {
        log.info("getChatList");

        List<Chat> chatList = chatRepository.findTop5ByRoomIdOrderBySendtimeAsc(roomId);

        chatList.forEach(chat -> log.info("service : getChatList" + chat.getContent()));

        return chatRepository.findTop5ByRoomIdOrderBySendtimeAsc(roomId);
    }

    @Override
    @Transactional
    public void insertChat(ChatDto chatDto) {
        log.info("insertChat : " + chatDto.toString());

        // 채팅 정보 저장
        Chat chat = new Chat();
        chat.setContent(chatDto.getContent());
        chat.setSender(chatDto.getSender());
        chat.setSendtime(chatDto.getSendtime());
        chat.setRoomId(chatDto.getRoomId());
        chatRepository.save(chat);

        // 채팅방 정보에 마지막 채팅 정보 수정
        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomsByRoomId(chatDto.getRoomId());
        if (chatRoomList == null) {
            new EntityNotFoundException();
        }

        chatRoomList.forEach(chatRoom -> chatRoom.updataChatRoom(chatDto.getContent(), chatDto.getSendtime()));

    }
}
