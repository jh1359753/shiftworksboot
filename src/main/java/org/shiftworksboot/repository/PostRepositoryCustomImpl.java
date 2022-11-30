package org.shiftworksboot.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.shiftworksboot.dto.PostDto;
import org.shiftworksboot.dto.PostSearchDto;
import org.shiftworksboot.entity.Post;
import org.shiftworksboot.entity.QPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private JPAQueryFactory jpaQueryFactory;

    public PostRepositoryCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }


    //제목으로 검색하기
    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if (StringUtils.hasText(searchBy)) {
            return QPost.post.pname.like("%" + searchQuery + "%");
        }
        return null;
    }





    @Override
    public Page<Post> getPostPage(PostSearchDto postSearchDto, Pageable pageable) {
        List<Post> postList = jpaQueryFactory
                .selectFrom(QPost.post)
                .where(searchByLike(postSearchDto.getSearchBy(),postSearchDto.getSearchQuery()))
                .orderBy(QPost.post.post_id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<Post>(postList,pageable,postList.size());
    }
}
