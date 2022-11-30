package org.shiftworksboot.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.shiftworksboot.entity.Schedule;

import java.util.List;

public interface ScheduleRepositoryCustom {

    List<Schedule> findMySchedule(String empId, String sch_group, String selectedDate);

}
