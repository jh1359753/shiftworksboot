package org.shiftworksboot.repository;

import org.shiftworksboot.dto.TaskDto;
import org.shiftworksboot.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepositoryCustom {

    Page<Task> getListWithPaging(TaskDto taskDto, Pageable pageable);
    Integer getTotalCount(TaskDto taskDto);

}
