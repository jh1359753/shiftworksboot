package org.shiftworksboot.entity;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.repository.ChatRepository;
import org.shiftworksboot.repository.ChatRoomRepository;
import org.shiftworksboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 통합테스트
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@Log4j2
class ChatTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("가장 최근 채팅 내용 가져오기")
    public void getChatList1(){

        insertChat();

        em.flush();
        em.clear();

        Chat chat = chatRepository.findTopChatByRoomIdOrderBySendtimeDesc("1");
        log.info("가장 최근 채팅 정보 : " + chat);

    }

    @Test
    @DisplayName("최근 채팅 내용 5개 가져오기")
    public void getChatList5(){

        insertChat();

        em.flush();
        em.clear();


        //List<Chat> chatList = chatRepository.findChats("1");
        //chatList.forEach(chat -> {log.info("최근 5개의 채팅 내역 : " + chat);});

    }

    public void insertChat(){

        Employee employee = new Employee();
        employee.setEmpId("user1");
        employee.setPassword("pw1");
        employee.setName("홍길동");
        employeeRepository.save(employee);

        //ChatRoom chatRoom = ChatRoom.createChatRoom(employee);
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomId("1");
        chatRoom.setEmployee(employee);
        chatRoomRepository.save(chatRoom);

        for (int i = 0; i < 5; i++) {
            Chat chat = new Chat();
            chat.setSender("user1");
            chat.setSendtime(LocalDateTime.now().toString());
            chat.setRoomId("1");
            chatRepository.save(chat);
        }

        //log.info("insertChat : " + chat.toString());

    }
}