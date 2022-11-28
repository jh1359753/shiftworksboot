package org.shiftworksboot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;


    @Test
    @DisplayName("게시판 생성 테스트")
    public void insertNewBoardTest(){
        BoardDto boardDto = new BoardDto();
        boardDto.setName("게시판");
        boardDto.setContent("게시판내용");
        boardDto.setPri("N");

         boardService.insertNewBoard(boardDto);



    }

    public void boardInsert(){

        for(int i =1;i<4;i++){
            Board board = new Board();
            board.setName("게시판"+i);
            board.setContent("게시판 내용"+i);
            board.setPri("N");
            boardRepository.save(board);
        }

    }

    @Test
    @DisplayName("게시판 목록 조회테스트")
    public void listBoardTest(){
        this.boardInsert();
        List<BoardDto>list = boardService.allBoardList();
        for(BoardDto dto:list){
            System.out.println(dto.toString());
        }
    }


    @Test
    @DisplayName("게시판 삭제 테스트")
    public void deleteBoard(){
        this.boardInsert();
        Integer b_id = 1;
        boardService.deleteBoard(b_id);

        List<BoardDto>list = boardService.allBoardList();
        for(BoardDto dto:list){
            System.out.println(dto.toString());
        }

    }



}