package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity insertNewBoard(BoardDto boardDto) {


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
    @GetMapping(value = "/boardmanage")
    public ModelAndView boardManage(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/board/BOA_boardmanage");

        return mav;
    }

    //게시판 목록 불러오기
    @GetMapping(value = "/allBoardList")
    public ResponseEntity allBoardList(){
        System.out.println("boardList....");

        return new ResponseEntity<List<BoardDto>>(boardService.allBoardList(),HttpStatus.OK);
    }

}
