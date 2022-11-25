package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.MeetingRoomFormDto;
import org.shiftworksboot.entity.MeetingRoom;
import org.shiftworksboot.repository.MeetingRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;


    @Transactional(readOnly = true)
    public MeetingRoom createMtr(MeetingRoomFormDto meetingRoomFormDto){
        MeetingRoom meetingRoom = meetingRoomFormDto.createMeetingRoom();

        meetingRoomRepository.save(meetingRoom);

        return meetingRoom;
    }




}
