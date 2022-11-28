package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.entity.Post;

@Getter @Setter
public class PostDto {

    private Integer post_id;

    private String pname;

    private String content;

    private String regdate;

    private String updatedate;

    //private char fix;

    private String receivedept;

    private int b_id;

    private int emp_id;

    private int dept_id;

    public static ModelMapper modelMapper = new ModelMapper();

    //dto -> entity
    public Post createPost(){
        return modelMapper.map(this, Post.class);
    }

    //entity -> dto
    public static PostDto of(Post post){
        return modelMapper.map(post, PostDto.class);
    }


}
