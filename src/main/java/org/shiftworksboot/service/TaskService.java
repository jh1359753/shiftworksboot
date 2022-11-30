package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.TaskDto;
import org.shiftworksboot.dto.TaskFileDto;
import org.shiftworksboot.dto.TaskFormDto;
import org.shiftworksboot.entity.Alarm;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.entity.Task;
import org.shiftworksboot.entity.TaskFile;
import org.shiftworksboot.repository.AlarmRepository;
import org.shiftworksboot.repository.EmployeeRepository;
import org.shiftworksboot.repository.TaskFileRepository;
import org.shiftworksboot.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskFileRepository taskFileRepository;
    private final AlarmRepository alarmRepository;

    private final EmployeeRepository employeeRepository;

    public void saveTask(TaskFormDto taskFormDto) {


        Task task = taskFormDto.createTask();
        Task savedTask = taskRepository.save(task);

        // 해당 업무 부서의 부서원들에게 알림 생성
        List<Employee> employees = employeeRepository.findByDepartmentDeptId(taskFormDto.getDept_id().toString());

        for(Employee e : employees) {

            Alarm alarm = new Alarm();
            alarm.setContent("[" + task.getTask_title() + "] 업무가 등록되었습니다.");

            alarm.setEmployee(employeeRepository.findByEmpId(e.getEmpId()));
            alarmRepository.save(alarm);

        }

        // 첨부파일이 없을 경우 메소드 종료
        if(taskFormDto.getFileList() == null || taskFormDto.getFileList().size() <= 0) {
            System.out.println("파일 없음");
            return;
        }

        System.out.println("파일 있음");
        taskFormDto.getFileList().forEach(file -> {
            TaskFile taskFile = file.createTaskFile();
            taskFile.setTask(savedTask);
            taskFileRepository.save(taskFile);
        });

    }

    public String deleteTask(Integer task_id) {

        if(task_id != null) {
            Task deleteTask = taskRepository.findById(task_id)
                    .orElseThrow(EntityNotFoundException::new);

            List<TaskFile> fileList = taskFileRepository.findByTask(deleteTask);

            // 첨부파일이 없을 경우 업무 삭제 후 메소드 종료
            if(fileList == null || fileList.size() <= 0) {
                taskRepository.deleteById(task_id);
                return "success";
            }

            fileList.forEach(file -> {
                taskFileRepository.delete(file);
            });

            taskRepository.deleteById(task_id);
            return "success";
        } else {
            return "fail";
        }
    }

}
