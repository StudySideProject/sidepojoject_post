package com.example.study_sideproject.post.repository;

import com.example.study_sideproject.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndMemberEmail(Long id, String email);
	boolean existsById(Long id);

}
