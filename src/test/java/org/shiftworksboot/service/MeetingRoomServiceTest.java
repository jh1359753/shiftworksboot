package org.shiftworksboot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.MeetingRoomFormDto;
import org.shiftworksboot.entity.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class MeetingRoomServiceTest {

    @Autowired
    private MeetingRoomService meetingRoomService;


    @Test
    @DisplayName("회의실 등록 테스트")
    public void insertMtr(){
        MeetingRoomFormDto meetingRoomFormDto = new MeetingRoomFormDto();
        meetingRoomFormDto.setRsc_name("이름");
        meetingRoomFormDto.setRsc_loc("위치");
        meetingRoomFormDto.setRsc_amount(100);

        System.out.println(meetingRoomService.createMtr(meetingRoomFormDto));


    }

}