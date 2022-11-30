package org.shiftworksboot.repository;

import org.shiftworksboot.dto.PostDto;
import org.shiftworksboot.dto.PostSearchDto;
import org.shiftworksboot.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostRepositoryCustom {

    Page<Post> getPostPage(PostSearchDto postSearchDto, Pageable pageable);
}
