package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Task;
import org.shiftworksboot.entity.TaskFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface TaskFileRepository extends JpaRepository<TaskFile, String>,
                                            QuerydslPredicateExecutor<TaskFile> {


    @Query("select file from TaskFile file " +
            "where file.task = :task")
    List<TaskFile> findByTask(Task task);
}
