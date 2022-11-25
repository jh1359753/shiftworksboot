package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Board;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class BoardDto {

    private Integer b_id;

    @NotBlank(message = "게시판명은 필수 입력값입니다")
    private String b_name;

    @NotBlank(message = "게시판 내용은 필수 입력값입니다")
    private String b_content;

    @NotBlank(message = "공개여부는 필수 입력값입니다")
    private char b_private;

    private static ModelMapper modelMapper = new ModelMapper();

    public Board createBoard(){
        return modelMapper.map(this,Board.class);
    }

}
