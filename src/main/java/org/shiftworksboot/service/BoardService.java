package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class BoardService {

    private final BoardRepository boardRepository;

    //게시판 생성하기
    public String insertNewBoard(BoardDto boardDto){
        Board board = boardDto.createBoard();

        //log.info(boardDto.getContent());
        //log.info(board.getContent());
        return boardRepository.save(board).getContent();

    }

    //공개게시판 목록 불러오기 수정예정
    public List<BoardDto> selectBoardList(){
        List<Board> list = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();

        for(Board board:list){
            BoardDto dto = BoardDto.of(board);
            dtos.add(dto);
        }
        return dtos;
    }


    //게시판 목록 불러오기
    public List<BoardDto> allBoardList(){
        List<Board> list = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();

        for(Board board:list){
          BoardDto dto = BoardDto.of(board);
          dtos.add(dto);
        }
        return dtos;
    }


    //게시판 삭제하기
    public void deleteBoard(Integer b_id){
        boardRepository.deleteById(b_id);
    }


}
