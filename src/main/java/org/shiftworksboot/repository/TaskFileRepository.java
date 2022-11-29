package org.shiftworksboot.repository;

import org.shiftworksboot.entity.TaskFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TaskFileRepository extends JpaRepository<TaskFile, String>,
                                            QuerydslPredicateExecutor<TaskFile> {

}
