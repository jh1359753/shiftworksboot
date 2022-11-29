package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.entity.Task;
import org.shiftworksboot.entity.TaskFile;
import org.shiftworksboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class TaskDto {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Integer task_id;
    private String task_title;
    private String task_content;
    private Character t_private;
    private Character notification;

    private String createBy;
    private String writer;

    // 첨부파일 list
    private List<TaskFileDto> fileList = new ArrayList<>();

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
        this.createBy = task.getCreatedBy();
    }

    public TaskDto() {}
}
