package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.MeetingRoom;

@Getter @Setter
public class MeetingRoomFormDto {

    private int rsc_id;

    private String rsc_name;
    private String rsc_loc;
    private int rsc_amount;
    //private String rsc_hrs; //이용시간 09시 ~ 19시 고정

    private static ModelMapper modelMapper = new ModelMapper();

    public MeetingRoom createMeetingRoom(){
        return modelMapper.map(this, MeetingRoom.class);
    }

    public static MeetingRoomFormDto of(MeetingRoom meetingRoom){
        return modelMapper.map(meetingRoom, MeetingRoomFormDto.class);
    }

}

