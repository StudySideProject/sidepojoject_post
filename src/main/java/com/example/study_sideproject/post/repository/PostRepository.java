package com.example.study_sideproject.post.repository;

import com.example.study_sideproject.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	boolean existsById(Long id);
}
