package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.MeetingRoomFormDto;
import org.shiftworksboot.service.MeetingRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MeetingRoomController {

    private MeetingRoomService meetingRoomService;

    @GetMapping(value = "/admin/mtr/new")
    public String meetingRoomForm(){
        //model.addAttribute("meetingRoomFormDto", new MeetingRoomFormDto());
        return "booking/BOK_insertRoomForm";
        //return "booking/BOK_insertForm";
    }

    @PostMapping(value = "/admin/mtr/new")
    public String createMtr(MeetingRoomFormDto meetingRoomFormDto){
        meetingRoomService.createMtr(meetingRoomFormDto);
        return "booking/BOK_list";
    }
}
