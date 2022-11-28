package org.shiftworksboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    @DisplayName("게시물 등록테스트")
    public void insertPostTest(){
        this.createBoard();
        /*Post post = new Post();

        post.setName("게시물");
        post.setContent("게시물 내용입니다");
        post.setRegno(0);
        post.setRegdate("2022-08-20");
        post.setUpdatedate("2022-08-20");
        post.setReceivedept("총무부");

        int b_id = 1;
        Board board= boardRepository.findById(b_id)
                        .orElseThrow(EntityNotFoundException::new);
        post.setBoard(board);

        System.out.println(postRepository.save(post));*/
    }

}