package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.shiftworksboot.entity.Alarm;

@Getter @Setter @ToString
public class AlarmDto {

    private Integer alarm_id;

    private String content;

    // 로그인 구현 후 수정
    // private String emp_id;
    // private String dept_id;
    // private String regDate;

    public AlarmDto() {
    }

    public AlarmDto(Alarm alarm) {
        this.alarm_id = alarm.getAlarm_id();
        this.content = alarm.getContent();
        // 로그인 구현 후 수정
        // this.emp_id = alarm.getEmp_id();
        // this.dept_id = alarm.getDept_id();
    }

}
