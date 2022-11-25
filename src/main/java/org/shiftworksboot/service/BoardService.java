package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Integer insertNewBoard(BoardDto boardDto){
        Board board = boardDto.createBoard();
        boardRepository.save(board);
        return board.getB_id();
    }


}
