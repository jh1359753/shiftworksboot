package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>,
                                            QuerydslPredicateExecutor<Schedule>,
                                            ScheduleRepositoryCustom {


}
