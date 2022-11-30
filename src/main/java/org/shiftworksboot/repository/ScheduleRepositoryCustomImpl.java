package org.shiftworksboot.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.shiftworksboot.entity.QSchedule;
import org.shiftworksboot.entity.Schedule;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom {

    private JPAQueryFactory queryFactory;
    public ScheduleRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Schedule> findMySchedule(String empId, String sch_group, String selectedDate) {

        // 선택일자가 없는 경우 null 리턴
        if(!StringUtils.hasText(selectedDate)) {
            return null;
        }

        String[] arr = selectedDate.split("-");
        int month = Integer.parseInt(arr[1]);
        int year = Integer.parseInt(arr[0]);

        String periodStart = null;
        String periodEnd = null;

        // 전월 22일 ~ 익월 6일까지의 일정
        if(month == 1) {
            periodStart = (year - 1) + "-" + (month+11) + "-22";
            periodEnd = year + "-0" + (month+1) + "-06";
        } else if(month > 1 && month < 9) {
            periodStart = year + "-0" + (month-1) + "-22";
            periodEnd = year + "-0" + (month+1) + "-06";
        } else if(month == 9) {
            periodStart = year + "-0" + (month-1) + "-22";
            periodEnd = year + "-" + (month+1) + "-06";
        } else if(month == 10) {
            periodStart = year + "-0" + (month-1) + "-22";
            periodEnd = year + "-" + (month+1) + "-06";
        } else if(month == 11) {
            periodStart = year + "-" + (month-1) + "-22";
            periodEnd = year + "-" + (month+1) + "-06";
        } else if(month == 12) {
            periodStart = year + "-" + (month-1) + "-22";
            periodEnd = (year+1) + "-0" + (month-11) + "-06";
        }



        QSchedule qSchedule = QSchedule.schedule;

        List<Schedule> list = queryFactory
                .selectFrom(qSchedule)
                .where(isOwn(empId),
                        selectGroup(sch_group),
                        period(periodStart, periodEnd))
                .fetch();

        return list;
    }

    // 나의 일정만 조회
    private BooleanExpression isOwn(String empId) {
        return StringUtils.hasText(empId) ? QSchedule.schedule.createdBy.eq(empId) : null;
    }

    // 선택 그룹에 대해서만 조회
    private BooleanExpression selectGroup(String sch_group) {
        return StringUtils.hasText(sch_group) ? QSchedule.schedule.sch_group.eq(sch_group) : null;
    }

    // 선택 날짜 기준 한 달만 조회
    private BooleanExpression period(String periodStart, String periodEnd) {

        return StringUtils.hasText(periodStart) && StringUtils.hasText(periodEnd) ?
                QSchedule.schedule.start_date.between(periodStart, periodEnd) : null ;
    }
}
