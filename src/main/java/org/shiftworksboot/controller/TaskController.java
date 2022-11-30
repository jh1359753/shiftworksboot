package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.dto.TaskDto;
import org.shiftworksboot.dto.TaskFileDto;
import org.shiftworksboot.dto.TaskFormDto;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.entity.Task;
import org.shiftworksboot.entity.TaskFile;
import org.shiftworksboot.repository.EmployeeRepository;
import org.shiftworksboot.repository.TaskFileRepository;
import org.shiftworksboot.repository.TaskRepository;
import org.shiftworksboot.service.TaskService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Log
public class TaskController {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskService taskService;
    private final TaskFileRepository taskFileRepository;


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

        Integer taskCount = taskRepository.getTotalCount(taskDto);

        int totalCount = taskCount%10 > 0 ? taskCount/10 + 1 : taskCount/10;

        // view로 결과를 전달하기 위한 ModelAndView
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/task/TSK_list");
        mav.addObject("tasks", tasks.getContent());
        mav.addObject("taskPage", tasks);
        mav.addObject("totalCount", --totalCount);

        return mav;
    }


    // 업무 상세 보기
    @GetMapping("/pages/{dept_id}/{type}/{keyword}/{pageNum}/{task_id}")
    public ModelAndView getTask(@PathVariable String dept_id, @PathVariable String type,
                                @PathVariable String keyword, @PathVariable Optional<Integer> pageNum,
                                @PathVariable Integer task_id, Authentication auth) {

        ModelAndView mav = new ModelAndView();


        // 선택 게시물 조회
        Task task = taskRepository.findById(task_id)
                .orElseThrow(EntityNotFoundException::new);

        // Task 객체를 DTO 객체로 변환
        TaskDto taskDto = new TaskDto(task);
        taskDto.setWriter(employeeRepository.findByEmpId(task.getCreatedBy()).getName());

        // 첨부파일 존재여부 확인
        List<TaskFile> fileList = taskFileRepository.findByTask(task);
        List<TaskFileDto> fileDtoList = new ArrayList<>();
        if(!(fileList == null || fileList.size() <= 0)) {
            fileList.forEach(file -> {
                fileDtoList.add(new TaskFileDto(file));
            });

            taskDto.setFileList(fileDtoList);
        }

        // 로그인 유저가 접근 권한이 없을 경우 denied 페이지로 이동
        UserDetails ud = (UserDetails) auth.getPrincipal();
        Employee emp = employeeRepository.findByEmpId(ud.getUsername());
        // 작성자는 확인할 수 있도록 추가
        if(!emp.getDepartment().getDeptId().equals(taskDto.getDept_id().toString())
            && !emp.getEmpId().equals(taskDto.getCreateBy())) {
            mav.setViewName("accessDenied");
            return mav;
        }

        // view로 결과를 전달하기 위한 ModelAndView
        mav.setViewName("/task/TSK_detail");
        // 조회한 업무 객체
        mav.addObject("task", taskDto);
        // 로그인 유저의 정보
        mav.addObject("emp", emp);

        return mav;
    }


    @PutMapping("/pages/{dept_id}/{type}/{keyword}/{pageNum}/{task_id}")
    public ResponseEntity<String> updateTask(@PathVariable String dept_id, @PathVariable String type,
                                             @PathVariable String keyword, @PathVariable Optional<Integer> pageNum,
                                             @PathVariable Integer task_id, @RequestBody TaskFormDto taskFormDto) {


        taskService.saveTask(taskFormDto);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    // 업무 삭제
    @DeleteMapping("/{task_id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer task_id) {

        return new ResponseEntity<String>(taskService.deleteTask(task_id), HttpStatus.OK);
    }

    // 파일 등록
    @PostMapping(value = "/uploadFile")
    public ResponseEntity<List<TaskFileDto>> uploadFile(MultipartFile[] uploadFile) {

        // 파일에 대하 정보를 리스트에 담아 리턴하기 위해 list 선언
        List<TaskFileDto> list = new ArrayList<TaskFileDto>();

        // 파일 업로드 폴더 경로
        String uploadFolder = "C:\\shiftworksboot\\upload";

        // 날짜에 맞는 업로드 폴더 존재 확인(없을 경우 생성)
        File uploadPath = new File(uploadFolder, getFolder());
        if(uploadPath.exists() == false) {
            uploadPath.mkdirs();
        } // 년/월/일 경로 생성

        // 업로드 파일을 하나씩 처리
        for(MultipartFile m : uploadFile) {

            TaskFileDto taskFile = new TaskFileDto();

            // 파일 저장 경로
            taskFile.setFile_src(getFolder());

            // uuid 생성
            UUID uuid = UUID.randomUUID();
            // uuid 파일객체에 저장
            taskFile.setUuid(uuid.toString());

            // 업로드 파일 실제 이름
            String uploadFileName = m.getOriginalFilename();
            // 파일명 vo객체에 저장
            taskFile.setFile_name(uploadFileName);

            // uuid + 실제 파일명
            uploadFileName = uuid.toString() + "_" + uploadFileName;

            try {
                // 파일 객체 생성
                File saveFile = new File(uploadPath, uploadFileName);
                // 실제 파일 업로드를 진행하는 메소드
                m.transferTo(saveFile);

                list.add(taskFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ResponseEntity<List<TaskFileDto>>(list, HttpStatus.OK);

    } // end uploadFile


    // 파일 다운로드
    @GetMapping(value="/download",
            // 다운로드가 가능하도록 MIME 타입 지정
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(String fileName) {

        FileSystemResource resource = new FileSystemResource("C:\\shiftworksboot\\upload\\" + fileName);

        // 다운로드 요청한 파일이 없는 경우
        if(resource.exists() == false) {
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }

        String resourceName = resource.getFilename();
        // C:\\upload에 저장된 파일명에서 uuid 제거
        String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

        HttpHeaders headers = new HttpHeaders();

        try {
            // 파일 다운로드 시 사용할 이름
            String downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");


            headers.add("Content-Disposition", "attachment; filename=" + downloadName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }


    // x버튼을 통한 파일 개별 삭제
    @Transactional
    @DeleteMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestBody TaskFileDto dto) {
        File file;

        log.info(dto.getFile_name());
        log.info(dto.getUuid());

        // DB까지 업로드된 경우 DB에서 해당 데이터 삭제
        if(dto.getUuid() != null) {
            TaskFile taskFile = dto.createTaskFile();
            taskFileRepository.deleteById(taskFile.getUuid());
        }

        try {
            // 삭제 대상을 파일 객체로 만듦
            file = new File("C:\\shiftworksboot\\upload\\" + dto.getFile_name());
            // 실제 파일이 존재하는 경우 삭제
            if(file.exists()) {
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>("삭제 완료", HttpStatus.OK);

    }


    // 메소드
    // 파일 저장 폴더를 위한 메소드
    private String getFolder() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String str = df.format(date);

        return str.replace("-", File.separator);
    }


}
