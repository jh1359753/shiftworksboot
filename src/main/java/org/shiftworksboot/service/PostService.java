package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shiftworksboot.dto.BoardDto;
import org.shiftworksboot.dto.PostDto;
import org.shiftworksboot.dto.PostSearchDto;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.entity.Post;
import org.shiftworksboot.repository.BoardRepository;
import org.shiftworksboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class PostService {

    private final PostRepository postRepository;

    private final BoardRepository boardRepository;

    //게시글 등록하기
    public void insertPost(PostDto postDto){
        Post post= postDto.createPost();

        //log.info("postDto:" +postDto.getContent());
        //log.info("post:"+ post.getContent());
        int b_id = postDto.getB_id();
        Board board = boardRepository.findById(b_id)
                .orElseThrow(EntityNotFoundException::new);
        post.setBoard(board);
        postRepository.save(post);

        //department, employee repository 통해 post 초기화하기

    }

    //게시글 목록 보기
    @Transactional(readOnly = true)
    public Page<Post> getListWithPaging(PostSearchDto postSearchDto, Pageable pageable){
        return postRepository.getPostPage(postSearchDto, pageable);
    }

    //게시글 상세보기
    public Post getPost(int post_id){
        return postRepository.findById(post_id)
                .orElseThrow(EntityNotFoundException::new);
    }








}
