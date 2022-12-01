package org.shiftworksboot.entity;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.constant.Role;
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
        department.setDeptId("1");
        department.setDept_name("개발팀");
        department.setAuthority(Role.USER);

        Employee employee = new Employee();
        employee.setEmpId("1");
        employee.setDepartment(department);
        employee.setName("홍길동");

        //employeeRepository.save(employee);

        return employee;
    }

    public void createChatRoom(){

        List<ChatRoom> chatRoomList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmpId("user3");
        employee.setPassword("pw3");
        employee.setName("홍길동");


        for (int i = 0; i < 10; i++){
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomId("roomid1");
            chatRoom.setEmployee(employee);
            chatRoom.setRoomName("테스트" + i);
            chatRoomList.add(chatRoom);
            chatRoomRepository.save(chatRoom);
        }


        log.info("chatRoomSave 테스트 - chatRoom" + chatRoomList);
    }

    @Test
    @DisplayName("emp_id로 채팅방 조회 테스트")
    public void findChatRooms() {

        createChatRoom();

//        Employee employee = createEmployee();
//        employeeRepository.save(employee);
//
//        for (int i = 1; i <= 3; i++) {
//            ChatRoom chatRoom = ChatRoom.createChatRoom(employee);
//
//            chatRoomRepository.save(chatRoom);
//        }

        em.flush();
        em.clear();

        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomByEmployee_EmpId("user1");

        log.info("chatroomList : "+chatRoomList.toString());
    }

    @Test
    @Transactional
    @DisplayName("roomId로 채팅방 조회 테스트")
    public void findChatRoomByRoomId() {
        createChatRoom();

        em.flush();
        em.clear();

        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomsByRoomId("roomid1");
        chatRoomList.forEach(chatRoom -> chatRoom.updataChatRoom("마지막 채팅 내용", "2022-12-01"));

        log.info("chatroomList : "+chatRoomList.toString());
    }
}