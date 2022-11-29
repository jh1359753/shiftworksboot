package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AlarmRepository extends JpaRepository<Alarm, Integer>,
                                        QuerydslPredicateExecutor<Alarm> {

}
