package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시판 생성페이지 이동
    @GetMapping(value = "/board/new")
    public ModelAndView register() throws Exception{

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_newboard");
        return mav;
    }


    //게시판 생성하기
    @PostMapping(value = "/newBoard")
    public ResponseEntity insertNewBoard(@Valid BoardDto boardDto,
                                                 BindingResult bindingResult,
                                                 Model model) {


        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(),HttpStatus.BAD_REQUEST);
        }

        Integer b_id;

        try{
            b_id = boardService.insertNewBoard(boardDto);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Integer>(b_id, HttpStatus.OK);

    }

}
