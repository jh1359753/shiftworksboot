package org.shiftworksboot.entity;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest // 통합테스트
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@Log4j2
class ChatRoomTest {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @PersistenceContext
    EntityManager em;

    // 사원 추가
    public Employee createEmployee(){

        Employee employee = new Employee();
        employee.setEmpId("1");
        employee.setName("홍길동");

        return employee;
    }

    public void createChatRoom(){

        List<ChatRoom> chatRoomList = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomName("테스트" + i);
            chatRoom.setEmployee(createEmployee());
            chatRoomList.add(chatRoom);
            chatRoomRepository.save(chatRoom);
        }


        //log.info("chatRoomSave 테스트 - chatRoom" + chatRoomList);
    }

    @Test
    @DisplayName("emp_id로 채팅방 조회 테스트")
    public void findChatRoomByEmployee_emp_idOrderByLastChat_time() {

        createChatRoom();

        em.flush();
        em.clear();

        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomsByEmployee_EmpIdOrderByLastchatTimeDesc("1");

        log.info("chatroomList : "+chatRoomList);
    }
}