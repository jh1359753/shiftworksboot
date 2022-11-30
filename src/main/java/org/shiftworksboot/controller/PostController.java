package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.PostDto;
import org.shiftworksboot.dto.PostSearchDto;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.entity.Post;
import org.shiftworksboot.repository.EmployeeRepository;
import org.shiftworksboot.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@Log
public class PostController {

    private final PostService postService;

    private final EmployeeRepository employeeRepository;


    //게시글 등록 form 이동
    @GetMapping (value = "/board/new")
    public ModelAndView register() throws Exception{

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_register");
        return mav;
    }


    //게시글 등록하기
    @PostMapping (value = "/board/new")
    public ResponseEntity register(@RequestBody PostDto postDto, Authentication auth){

        //파일업로드확인
//        if(vo.getFileList() !=null) {
//            vo.getFileList().forEach(file -> log.info(file));
//        }
        UserDetails ud = (UserDetails)auth.getPrincipal();
        String emp_id = ud.getUsername();

        //log.info("employee:"+ emp_id);


        postService.insertPost(postDto,emp_id);
        return new ResponseEntity<String>("success", HttpStatus.OK);

    }


    //게시판 번호에 맞는 리스트 호출
    @GetMapping(value = "/board/list")
    public ModelAndView getList(PostSearchDto postSearchDto) {

        //log.info("getList..........");

        Pageable pageable = PageRequest.of(0,3);
        Page<Post> posts = postService.getListWithPaging(postSearchDto,pageable);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_list");
        mav.addObject("posts",posts.getContent());
        mav.addObject("total",posts.getTotalPages());
        log.info("posts"+ posts.getContent());


        return mav;
    }


    //글번호 클릭 시 BOA_get.jsp로 이동
    @GetMapping(value = "/board/get")
    public ModelAndView getPost(@RequestParam("post_id") int post_id) throws Exception{
        //log.info("get.........");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_get");
        mav.addObject("post", postService.getPost(post_id));
        return mav;
    }

    //수정 클릭 시 BOA_modify.jsp로 이동
    @GetMapping(value = "/board/modify")
    public ModelAndView modify(@RequestParam("post_id") int post_id) throws Exception{
        //log.info("modify.........");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_modify");
        mav.addObject("post", postService.getPost(post_id));
        return mav;
    }



}
