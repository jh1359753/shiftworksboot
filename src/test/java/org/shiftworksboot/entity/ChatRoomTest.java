package org.shiftworksboot.entity;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.repository.ChatRoomRepository;
import org.shiftworksboot.repository.EmployeeRepository;
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

    @Autowired
    EmployeeRepository employeeRepository;

    @PersistenceContext
    EntityManager em;

    // 사원 추가
    public Employee createEmployee(){

        Department department = new Department();
        department.setDept_id("1");
        department.setDept_name("개발팀");
        department.setAuthority("USER");

        Employee employee = new Employee();
        employee.setEmp_id("1");
        //employee.setDepartment(department);
        employee.setName("홍길동");

        employeeRepository.save(employee);

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


        log.info("chatRoomSave 테스트 - chatRoom" + chatRoomList);
    }

    @Test
    @DisplayName("emp_id로 채팅방 조회 테스트")
    public void findChatRooms() {

        //createChatRoom();

        Employee employee = createEmployee();

        for (int i = 0; i < 3; i++) {
            ChatRoom chatRoom = ChatRoom.createChatRoom();
            chatRoom.setEmployee(employee);
            chatRoomRepository.save(chatRoom);
        }

        em.flush();
        em.clear();

        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRooms("1");

        log.info("chatroomList : "+chatRoomList.toString());
    }
}