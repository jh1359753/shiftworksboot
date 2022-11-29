package org.shiftworksboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.PostDto;
import org.shiftworksboot.dto.PostSearchDto;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BoardRepository boardRepository;

    public void createBoard(){
        Board board = new Board();
        board.setName("게시판");
        board.setContent("게시판입니다");
        board.setPri("N");
        boardRepository.save(board);
    }

    public void createPost(){

        for(int i=0;i<5;i++){
            Post post = new Post();

            post.setPname("게시물"+i);
            post.setContent("게시물 내용입니다"+i);
            post.setRegno(0);
            post.setRegdate("2022-08-20");
            post.setUpdatedate("2022-08-20");
            post.setReceivedept("총무부");

            int b_id = 1;
            Board board= boardRepository.findById(b_id)
                    .orElseThrow(EntityNotFoundException::new);
            post.setBoard(board);

            postRepository.save(post);
        }


    }

    @Test
    @DisplayName("게시물 등록테스트")
    public void insertPostTest(){
        this.createBoard();
        Post post = new Post();

        post.setPname("게시물");
        post.setContent("게시물 내용입니다");
        post.setRegno(0);
        post.setRegdate("2022-08-20");
        post.setUpdatedate("2022-08-20");
        post.setReceivedept("총무부");

        int b_id = 1;
        Board board= boardRepository.findById(b_id)
                        .orElseThrow(EntityNotFoundException::new);
        post.setBoard(board);

        System.out.println(postRepository.save(post));
    }

    @Test
    @DisplayName("게시물 조회 테스트")
    public void getListWithPagingTest(){
        this.createBoard();
        this.createPost();

        PostSearchDto postSearchDto = new PostSearchDto();
        postSearchDto.setSearchBy("T");
        postSearchDto.setSearchQuery("2");
        Pageable pageable = PageRequest.of(0,3);

        System.out.println("ddd:"+ postRepository.getPostPage(postSearchDto,pageable).getContent());
    }


    @Test
    @DisplayName("게시물 조회 테스트2")
    public void getListTest(){
        this.createBoard();
        this.createPost();

        System.out.println(postRepository.findAll());
    }

}