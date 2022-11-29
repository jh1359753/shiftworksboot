package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.dto.TaskDto;
import org.shiftworksboot.dto.TaskFormDto;
import org.shiftworksboot.entity.Task;
import org.shiftworksboot.repository.TaskRepository;
import org.shiftworksboot.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Log
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;


    // 업무 등록을 위한 폼으로 이동
    @GetMapping("/new")
    public ModelAndView insertForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/task/TSK_new");
        return mav;
    }

    // 업무 등록(save) 요청
    @PostMapping("/new")
    public ResponseEntity<String> insert(@RequestBody TaskFormDto taskFormDto) {

        taskService.saveTask(taskFormDto);

        return new ResponseEntity<String>("업무가 등록되었습니다.", HttpStatus.OK);
    }

    // 업무 목록 출력
    @GetMapping("/pages/{dept_id}/{type}/{keyword}/{pageNum}")
    public ModelAndView getList(@PathVariable String dept_id, @PathVariable String type,
                                @PathVariable String keyword, @PathVariable Integer pageNum) {

        TaskDto taskDto = new TaskDto();

        // 검색 조건 처리
        if(!dept_id.equals("all")) {
            dept_id = dept_id.toUpperCase();
            taskDto.setDept_id(TaskDept.valueOf(dept_id));
        } else {
            taskDto.setDept_id(null);
        }
        if(type.equals("t")) {
            taskDto.setTask_title(keyword);
        }
        if(type.equals("c")) {
            taskDto.setTask_content(keyword);
        }

        // 페이징 처리를 위한 객체 생성
        Pageable pageable = PageRequest.of(--pageNum, 10);

        Page<Task> tasks = taskRepository.getListWithPaging(taskDto, pageable);

        // view로 결과를 전달하기 위한 ModelAndView
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/task/TSK_list");
        mav.addObject("tasks", tasks.getContent());
        mav.addObject("taskPage", tasks);
        mav.addObject("totalPages", tasks.getTotalPages());

        return mav;
    }


    // 업무 상세 보기
    @GetMapping("/pages/{dept_id}/{type}/{keyword}/{pageNum}/{task_id}")
    public ModelAndView getTask(@PathVariable String dept_id, @PathVariable String type,
                                @PathVariable String keyword, @PathVariable Optional<Integer> pageNum,
                                @PathVariable Integer task_id) {

        // 선택 게시물 조회
        Task task = taskRepository.findById(task_id)
                .orElseThrow(EntityNotFoundException::new);

        // Task 객체를 DTO 객체로 변환
        TaskDto taskDto = new TaskDto(task);

        // view로 결과를 전달하기 위한 ModelAndView
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/task/TSK_detail");
        mav.addObject("task", taskDto);

        return mav;
    }


    @PutMapping("/pages/{dept_id}/{type}/{keyword}/{pageNum}/{task_id}")
    public ResponseEntity<String> updateTask(@PathVariable String dept_id, @PathVariable String type,
                                             @PathVariable String keyword, @PathVariable Optional<Integer> pageNum,
                                             @PathVariable Integer task_id, @RequestBody TaskFormDto taskFormDto) {


        taskService.saveTask(taskFormDto);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer task_id) {

        return new ResponseEntity<String>(taskService.deleteTask(task_id), HttpStatus.OK);
    }

}
