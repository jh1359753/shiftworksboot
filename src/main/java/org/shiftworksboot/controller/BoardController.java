package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log
public class BoardController {

    private final BoardService boardService;

    //게시판 생성페이지 이동
    @GetMapping(value = "/board/newBoard")
    public ModelAndView register() throws Exception{

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_newboard");
        return mav;
    }


    //게시판 생성하기
    @PostMapping(value = "/board/newBoard")
    public ResponseEntity insertNewBoard(@Valid @RequestBody BoardDto boardDto,
                                         BindingResult bindingResult) {
        log.info("new............");


        /*if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(),HttpStatus.BAD_REQUEST);
        }*/

        String content = boardService.insertNewBoard(boardDto);

        return new ResponseEntity<String>(content, HttpStatus.OK);

    }


    //게시판 관리 페이지 이동하기
    @GetMapping(value = "/board/boardmanage")
    public ModelAndView boardManage(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_boardmanage");

        return mav;
    }

    //공개게시판 목록 불러오기
    @GetMapping(value = "/board/boardList")
    public ResponseEntity<List<BoardDto>> selectBoardList(){

        //log.info("boardList.......");
        return new ResponseEntity<List<BoardDto>>(boardService.selectBoardList(),HttpStatus.OK);
    }


    //게시판 목록 불러오기
    @GetMapping(value = "/board/allBoardList")
    public ResponseEntity allBoardList(){
        System.out.println("boardList....");

        return new ResponseEntity<List<BoardDto>>(boardService.allBoardList(),HttpStatus.OK);
    }


    //게시판 삭제하기
    @DeleteMapping(value = "/board/deleteBoard/{b_id}")
    public ResponseEntity deleteBoard(@PathVariable("b_id") int b_id){

        boardService.deleteBoard(b_id);
        return new ResponseEntity<String>("삭제완료", HttpStatus.OK);
    }


}
