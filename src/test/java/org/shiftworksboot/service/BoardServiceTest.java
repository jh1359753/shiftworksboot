package org.shiftworksboot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시판 생성 테스트")
    public void insertNewBoardTest(){
        BoardDto boardDto = new BoardDto();
        boardDto.setB_content("게시판 생성테스트");
        boardDto.setB_private('N');
        boardDto.setB_name("게시판1");

       Integer b_id =boardService.insertNewBoard(boardDto);
       System.out.println("게시판 아이디:"+b_id);
    }



}