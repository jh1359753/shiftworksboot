package org.shiftworksboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.entity.Board;
import org.shiftworksboot.entity.Post;
import org.shiftworksboot.entity.Scrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class ScrapRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private BoardRepository boardRepository;

    public void createBoard(){
        Board board = new Board();
        board.setName("게시판");
        board.setContent("게시판입니다");
        board.setPri("N");
        boardRepository.save(board);
    }

    public void createPost() {

        for (int i = 0; i < 5; i++) {
            Post post = new Post();

            post.setPname("게시물" + i);
            post.setContent("게시물 내용입니다" + i);
            post.setRegno(0);
            post.setRegdate("2022-08-20");
            post.setUpdatedate("2022-08-20");
            post.setReceivedept("총무부");

            int b_id = 1;
            Board board = boardRepository.findById(b_id)
                    .orElseThrow(EntityNotFoundException::new);
            post.setBoard(board);

            postRepository.save(post);
        }
    }

    @Test
    @DisplayName("스크랩 테스트")
    public void ScrapPostTest(){
        this.createBoard();
        this.createPost();

        Scrap scrap = new Scrap();
        scrap.setScrap_date("2022-08-20");

        int post_id = 2;
        Post post = postRepository.findById(post_id)
                        .orElseThrow(EntityNotFoundException::new);
        scrap.setPost(post);

        System.out.println(scrapRepository.save(scrap).getScrap_id());
    }

    @Test
    @DisplayName("스크랩 조회 테스트")
    public void getListScrap(){

    }





}