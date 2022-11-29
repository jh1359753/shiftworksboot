package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.TaskDto;
import org.shiftworksboot.dto.TaskFormDto;
import org.shiftworksboot.entity.Alarm;
import org.shiftworksboot.entity.Task;
import org.shiftworksboot.repository.AlarmRepository;
import org.shiftworksboot.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final AlarmRepository alarmRepository;

    public void saveTask(TaskFormDto taskFormDto) {

        Task task = taskFormDto.createTask();
        Alarm alarm = new Alarm();

        // 로그인 기능 추가 후 수정
        alarm.setContent("[" + task.getTask_title() + "] 업무가 등록되었습니다.");

        taskRepository.save(task);
        alarmRepository.save(alarm);

    }

    public String deleteTask(Integer task_id) {

        if(task_id != null) {
            taskRepository.deleteById(task_id);
            return "success";
        } else {
            return "fail";
        }
    }

}
