package org.shiftworksboot.repository;

import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.ScheduleFormDto;
import org.shiftworksboot.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@Log
class ScheduleRepositoryTest {

    @Autowired
    ScheduleRepository scheduleRepository;


    @Test
    @DisplayName("일정 조회 테스트")
    public void findMySch() {

        ScheduleFormDto dto = new ScheduleFormDto();

        dto.setStart_date("2022-10-22 09:00");

        scheduleRepository.save(dto.createSchedule());

        List<Schedule> list = scheduleRepository.findMySchedule(null, null, "2022-10-06");

        for(Schedule s : list) {
            log.info("내용: " + s.toString());
        }

    }


}