package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Board;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
public class BoardDto {

    private Integer b_id;

    @NotBlank(message = "게시판명은 필수 입력값입니다")
    private String name;

    @NotBlank(message = "게시판 내용은 필수 입력값입니다")
    private String content;

    @NotBlank(message = "공개여부는 필수 입력값입니다")
    private String pri;

    private static ModelMapper modelMapper = new ModelMapper();

    //dto -> entity
    public Board createBoard(){
        return modelMapper.map(this,Board.class);
    }

    //entity -> dto
    public static BoardDto of(Board board){
        return modelMapper.map(board, BoardDto.class);
    }

}
