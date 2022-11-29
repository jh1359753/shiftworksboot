package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.TaskDto;
import org.shiftworksboot.dto.TaskFormDto;
import org.shiftworksboot.entity.Alarm;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.entity.Task;
import org.shiftworksboot.repository.AlarmRepository;
import org.shiftworksboot.repository.EmployeeRepository;
import org.shiftworksboot.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final AlarmRepository alarmRepository;

    private final EmployeeRepository employeeRepository;

    public void saveTask(TaskFormDto taskFormDto) {


        Task task = taskFormDto.createTask();

        taskRepository.save(task);

        Alarm alarm = new Alarm();
        alarm.setContent("[" + task.getTask_title() + "] 업무가 등록되었습니다.");

        // 해당 업무 부서의 부서원들에게 알림 생성
        //List<Employee> employees = employeeRepository.findByDeptId(taskFormDto.getDept_id());

        //alarmRepository.save(alarm);



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
