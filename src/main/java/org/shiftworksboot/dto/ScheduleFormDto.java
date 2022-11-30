package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Schedule;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class ScheduleFormDto {

    private Integer sch_id;
    // private Booking booking;
    private String sch_title;
    private String sch_content;

    private String start_date;
    private String end_date;

    private String sch_group;
    private String createdBy;

    private static ModelMapper modelMapper = new ModelMapper();

    public Schedule createSchedule() {
        return modelMapper.map(this, Schedule.class);
    }

}
