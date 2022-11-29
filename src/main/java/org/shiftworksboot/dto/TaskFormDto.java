package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.entity.Task;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
public class TaskFormDto {

    private Integer task_id;
    private String task_title;
    private String task_content;
    private Character t_private;
    private Character notification;
    private TaskDept dept_id;

    // Task 객체 - TaskFormDto 객체 변환을 위한 모델 매퍼
    private static ModelMapper modelMapper = new ModelMapper();

    public Task createTask() {
        // 매핑전략(dept_id가 task_id에 매핑되는 것 방지)
        modelMapper.typeMap(TaskFormDto.class, Task.class).addMappings(mapper -> {
            mapper.map(TaskFormDto::getDept_id, Task::setDept_id);
        });
        return modelMapper.map(this, Task.class);
    }

}
