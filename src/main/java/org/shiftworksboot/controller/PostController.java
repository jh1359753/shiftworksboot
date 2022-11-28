package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.PostDto;
import org.shiftworksboot.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    //게시글 등록 form 이동
    @GetMapping (value = "/board/new")
    public ModelAndView register() throws Exception{

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_register");
        return mav;
    }


    //게시글 등록하기
    @PostMapping (value = "/board/new")
    public ResponseEntity register(@RequestBody PostDto postDto){

        //파일업로드확인
//        if(vo.getFileList() !=null) {
//            vo.getFileList().forEach(file -> log.info(file));
//        }

        postService.insertPost(postDto);
        return new ResponseEntity<String>("success", HttpStatus.OK);

    }



}
