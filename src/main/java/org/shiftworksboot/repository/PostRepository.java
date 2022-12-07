package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>,
PostRepositoryCustom{
}
