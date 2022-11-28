package org.shiftworksboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 생성 테스트")
    public void insertNewBoardTest(){
        BoardDto boardDto = new BoardDto();
        boardDto.setName("게시판");
        boardDto.setContent("게시판 내용");
        boardDto.setPri("N");
        Board board = boardDto.createBoard();

        System.out.println("게시판" +boardRepository.save(board).getContent());

    }

    @Test
    @DisplayName("게시판 목록 불러오기")
    public void allBoardListTest(){
        Board board = new Board();
        board.setName("게시판");
        board.setContent("게시판 내용");
        board.setPri("N");

        boardRepository.save(board);

        System.out.println("게시판: " +boardRepository.findAll());

    }


}