package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TaskRepository extends JpaRepository<Task, Integer>,
                                        QuerydslPredicateExecutor<Task>,
                                        TaskRepositoryCustom{



}
