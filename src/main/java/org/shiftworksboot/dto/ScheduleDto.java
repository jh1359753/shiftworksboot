package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.shiftworksboot.entity.Booking;
import org.shiftworksboot.entity.Schedule;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class ScheduleDto {

    private Integer sch_id;
    // private Booking booking;
    private String sch_title;
    private String sch_content;
    private String start_date;
    private String end_date;
    private String sch_group;
    private String createdBy;
    private String sch_date;

    public ScheduleDto() {}

    public ScheduleDto(Schedule schedule) {
        this.sch_id = schedule.getSch_id();
        this.sch_title = schedule.getSch_title();
        this.sch_content = schedule.getSch_content();
        this.start_date = schedule.getStart_date();
        this.end_date = schedule.getEnd_date();
        this.sch_group = schedule.getSch_group();
        this.createdBy = schedule.getCreatedBy();
    }

}
