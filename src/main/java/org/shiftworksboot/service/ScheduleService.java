package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.ScheduleDto;
import org.shiftworksboot.dto.ScheduleFormDto;
import org.shiftworksboot.entity.Schedule;
import org.shiftworksboot.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 목록
    public List<ScheduleDto> findMySchedule(String empId, String sch_group, String selectedDate) throws ParseException {

        List<Schedule> list = scheduleRepository.findMySchedule(empId, sch_group, selectedDate);

        List<ScheduleDto> dtoList = new ArrayList<>();

        for(Schedule s : list) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date start = dateFormat.parse(s.getStart_date());
            Date end = dateFormat.parse(s.getEnd_date());
            Date sch_date = start;


            // 시작일~종료일 기간에 일정 객체 생성
            while (sch_date.getTime() <= end.getTime()) {
                ScheduleDto dto = new ScheduleDto(s);
                dto.setSch_date(dateFormat.format(sch_date));

                // 반환 리스트에 객체 추가
                dtoList.add(dto);

                // sch_date 1일 증가
                Calendar cal = Calendar.getInstance();
                cal.setTime(sch_date);
                cal.add(Calendar.DATE, 1);
                sch_date = new Date(cal.getTimeInMillis());

            } // end while

        } // end forEach schedule


        log.info("개수: " + dtoList.size());

        return dtoList;
    }// findMySchedule()


    // 일정 개별
    public ScheduleDto findSchedule(Integer sch_id) {

        ScheduleDto dto = new ScheduleDto(scheduleRepository.findById(sch_id)
                .orElseThrow(EntityNotFoundException::new));

        return dto;
    }



    // 일정 등록, 수정
    public Schedule saveSchedule(ScheduleFormDto scheduleFormDto) {

        Schedule schedule = scheduleFormDto.createSchedule();

        return scheduleRepository.save(schedule);

    }

    // 일정 삭제
    public void deleteSchedule(Integer sch_id) {

        scheduleRepository.deleteById(sch_id);

    }



}
