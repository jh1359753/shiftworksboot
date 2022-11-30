package org.shiftworksboot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class PostServiceTest {

    @Autowired
    private PostService postService;


    /*@Test
    @DisplayName("게시판 목록 조회 테스트")
    public void getListWithPagingTest(){
        PostDto postDto = new PostDto();

        Pageable pageable = PageRequest.of(0,3);
        postService.getListWithPaging(postDto, pageable);
    }*/

}