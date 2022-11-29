package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.entity.Task;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter @ToString
public class TaskDto {

    private Integer task_id;
    private String task_title;
    private String task_content;
    private Character t_private;
    private Character notification;

    @Enumerated(EnumType.STRING)
    private TaskDept dept_id;


    // Task 객체를 Task Dto로 변환
    public TaskDto(Task task) {
        this.task_id = task.getTask_id();
        this.task_title = task.getTask_title();
        this.task_content = task.getTask_content();
        this.t_private = task.getT_private();
        this.notification = task.getNotification();
        this.dept_id = task.getDept_id();
    }

    public TaskDto() {}
}
