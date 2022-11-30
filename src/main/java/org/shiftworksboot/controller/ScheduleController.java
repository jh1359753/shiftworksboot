package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.ScheduleDto;
import org.shiftworksboot.dto.ScheduleFormDto;
import org.shiftworksboot.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
@Log
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/main")
    public ModelAndView schedule(Authentication auth) {
        ModelAndView mav = new ModelAndView();

        UserDetails ud = (UserDetails) auth.getPrincipal();
        log.info(ud.getUsername());

        mav.addObject("principal", ud);
        mav.setViewName("/schedule/SCH_list");
        return mav;
    }

    // 그룹별, 월별 일정 가져오기
    @GetMapping(value="/{sch_group}/{selectedDate}")
    public ResponseEntity<List<ScheduleDto>> getList(
            @PathVariable String sch_group, @PathVariable String selectedDate, Authentication auth) {

        // 스케줄 그룹별 보기 미선택 시 null 적용하여 전체 일정 조회
        if(sch_group.equals("all")) {
            sch_group = null;
        }

        log.info("선택일: " + selectedDate);
        log.info("선택그룹: " + sch_group);

        // 로그인한 사람의 일정만 볼 수 있도록 토큰에서 로그인 사용자 정보 추출
        UserDetails ud = (UserDetails) auth.getPrincipal();

        List<ScheduleDto> list = new ArrayList<>();

        try {
            list = scheduleService.findMySchedule(ud.getUsername(), sch_group, selectedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<ScheduleDto>>(list, HttpStatus.OK);
    }

    // 일정 상세 보기
    // 일정 상세 보기
    @GetMapping(value="/{sch_id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Integer sch_id) {

        ScheduleDto schedule = scheduleService.findSchedule(sch_id);

        return new ResponseEntity<ScheduleDto>(schedule, HttpStatus.OK);
    }


    // 일정 등록
    @PostMapping(value="/new",
            produces= MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> insertSchedule(@RequestBody ScheduleFormDto dto) {

        return scheduleService.saveSchedule(dto) != null ?
                new ResponseEntity<String>("success", HttpStatus.OK) :
                new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // 일정 수정
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT},
            value="/{sch_id}",
            produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> updateSchedule(@RequestBody ScheduleFormDto dto) {

        return scheduleService.saveSchedule(dto) != null ?
                new ResponseEntity<String>("success", HttpStatus.OK) :
                new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 일정 삭제
    @DeleteMapping(value="/{sch_id}",
            produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteSchedule(@PathVariable Integer sch_id) {
        scheduleService.deleteSchedule(sch_id);
        return new ResponseEntity<String>("success", HttpStatus.OK);

    }


}
