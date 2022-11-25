package org.shiftworksboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.entity.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class MeetingRoomRepositoryTest {

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    @Test
    @DisplayName("회의실 등록 테스트")
    public void insertMtr(){
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setRsc_name("이름");
        meetingRoom.setRsc_loc("위치");
        meetingRoom.setRsc_amount(100);


        System.out.println(meetingRoomRepository.save(meetingRoom));
    }
}